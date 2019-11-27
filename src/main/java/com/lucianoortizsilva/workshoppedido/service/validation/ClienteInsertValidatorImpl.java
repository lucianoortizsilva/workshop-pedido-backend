package com.lucianoortizsilva.workshoppedido.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.lucianoortizsilva.workshoppedido.domain.enums.TipoCliente;
import com.lucianoortizsilva.workshoppedido.dto.ClienteNewDTO;
import com.lucianoortizsilva.workshoppedido.exception.FieldMessage;
import com.lucianoortizsilva.workshoppedido.service.validation.utils.BR;

public class ClienteInsertValidatorImpl implements ConstraintValidator<ClienteInsertValidator, ClienteNewDTO> {
	
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

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		
		return list.isEmpty();
	}
}
