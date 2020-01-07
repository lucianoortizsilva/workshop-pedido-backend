package com.lucianoortizsilva.workshoppedido.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.lucianoortizsilva.workshoppedido.config.security.autenticacao.UserSpringSecurity;
import com.lucianoortizsilva.workshoppedido.domain.Cidade;
import com.lucianoortizsilva.workshoppedido.domain.Cliente;
import com.lucianoortizsilva.workshoppedido.domain.Endereco;
import com.lucianoortizsilva.workshoppedido.domain.enums.Perfil;
import com.lucianoortizsilva.workshoppedido.domain.enums.TipoCliente;
import com.lucianoortizsilva.workshoppedido.dto.ClienteDTO;
import com.lucianoortizsilva.workshoppedido.dto.ClienteNewDTO;
import com.lucianoortizsilva.workshoppedido.exception.AuthorizationException;
import com.lucianoortizsilva.workshoppedido.exception.DataIntegrityException;
import com.lucianoortizsilva.workshoppedido.exception.ObjectNotFoundException;
import com.lucianoortizsilva.workshoppedido.repositories.ClienteRepository;
import com.lucianoortizsilva.workshoppedido.repositories.EnderecoRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private S3Service s3Service;
	
	public Cliente find(Integer id) {
		final UserSpringSecurity userSpringSecurity = UserService.authenticated(); 
		
		if(userSpringSecurity == null || !userSpringSecurity.hasRole(Perfil.ADMIN) && !id.equals(userSpringSecurity.getId())) {
			throw new AuthorizationException("Acesso Negado!");	
		}
		
		Optional<Cliente> obj = this.repository.findById(id);
		if (!obj.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id);
		}
		return obj.get();
	}

	public List<Cliente> findAll() {
		return this.repository.findAll();
	}

	public Page<Cliente> findPage(Integer qtdPaginas, Integer qtdLinhas, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(qtdPaginas, qtdLinhas, Direction.valueOf(direction), orderBy);
		return this.repository.findAll(pageRequest);
	}

	public Cliente update(Cliente obj) {
		final Cliente newObj = this.find(obj.getId());
		updateData(newObj, obj);
		return this.repository.save(newObj);
	}

	private static void updateData(final Cliente newObj, final Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail()); 
	}

	public void delete(Integer id) {
		this.find(id);
		try {
			this.repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");
		}
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = this.repository.save(obj);
		this.enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	public Cliente fromDTO(ClienteDTO dto) {
		return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null, null);
	}

	public Cliente fromDTO(ClienteNewDTO dto) {
		final Cliente cliente = new Cliente(null, dto.getNome(), dto.getEmail(), dto.getCpfOuCnpj(), TipoCliente.toEnum(dto.getTipo()), this.passwordEncoder.encode(dto.getSenha()));
		final Cidade cidade = new Cidade(dto.getCidadeId(), null, null);
		final Endereco endereco = new Endereco(null, dto.getLogradouro(), dto.getNumero(), dto.getComplemento(), dto.getBairro(), dto.getCep(), cliente, cidade);
		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().add(dto.getTelefone1());
		if(!StringUtils.isEmpty(dto.getTelefone2())) {
			cliente.getTelefones().add(dto.getTelefone2());
		}
		if(!StringUtils.isEmpty(dto.getTelefone3())) {
			cliente.getTelefones().add(dto.getTelefone3());
		}
		return cliente;
	}

	public URI uploadProfilePicture(MultipartFile multipartFile) {
		return	this.s3Service.uploadFile(multipartFile);
	}
	
}
