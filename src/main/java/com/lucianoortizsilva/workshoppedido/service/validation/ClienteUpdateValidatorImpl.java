package com.lucianoortizsilva.workshoppedido.service.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.lucianoortizsilva.workshoppedido.domain.Cliente;
import com.lucianoortizsilva.workshoppedido.dto.ClienteDTO;
import com.lucianoortizsilva.workshoppedido.exception.FieldMessage;
import com.lucianoortizsilva.workshoppedido.repositories.ClienteRepository;

public class ClienteUpdateValidatorImpl implements ConstraintValidator<ClienteUpdateValidator, ClienteDTO> {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void initialize(ClienteUpdateValidator ann) {
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		
		final Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		final Integer clienteId = Integer.parseInt(map.get("id"));
		
		final List<FieldMessage> list = new ArrayList<>();

		final Cliente cliente = this.clienteRepository.findByEmail(objDto.getEmail());

		if (!clienteId.equals(cliente.getId()) && cliente != null) {
			list.add(new FieldMessage("email", "E-mail já existente!"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}

		return list.isEmpty();
	}
}
