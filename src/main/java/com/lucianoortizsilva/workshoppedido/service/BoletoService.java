package com.lucianoortizsilva.workshoppedido.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.lucianoortizsilva.workshoppedido.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoComBoleto pagamentoComBoleto, Date dtPedido) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dtPedido);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		pagamentoComBoleto.setDataVencimento(calendar.getTime());
	}

}
