package com.lucianoortizsilva.workshoppedido.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.lucianoortizsilva.workshoppedido.domain.Cliente;
import com.lucianoortizsilva.workshoppedido.domain.enums.TipoCliente;
import com.lucianoortizsilva.workshoppedido.dto.ClienteNewDTO;
import com.lucianoortizsilva.workshoppedido.exception.FieldMessage;
import com.lucianoortizsilva.workshoppedido.repositories.ClienteRepository;
import com.lucianoortizsilva.workshoppedido.service.validation.utils.BR;

public class ClienteInsertValidatorImpl implements ConstraintValidator<ClienteInsertValidator, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository clienteRepository; 
	
	@Override
	public void initialize(ClienteInsertValidator ann) {}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if(TipoCliente.PESSOA_FISICA.equals(TipoCliente.toEnum(objDto.getTipo())) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF Inválido!"));
		}
		
		if(TipoCliente.PESSOA_JURIDICA.equals(TipoCliente.toEnum(objDto.getTipo()))&& !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ Inválido!"));
		}
		
		Cliente cliente = this.clienteRepository.findByEmail(objDto.getEmail());
		if(cliente != null) {
			list.add(new FieldMessage("email", "E-mail já existente!"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		
		return list.isEmpty();
	}
}
