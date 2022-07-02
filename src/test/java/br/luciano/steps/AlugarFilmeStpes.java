package br.luciano.steps;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;

import br.luciano.entidades.Filme;
import br.luciano.entidades.NotaAluguel;
import br.luciano.servicos.AluguelService;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class AlugarFilmeStpes {

	private Filme filme;
	private AluguelService aluguel = new AluguelService();
	private NotaAluguel notaAluguel;
	
	@Dado("um fillme com estoque de {int} unidades")
	public void umFillmeComEstoqueDeUnidades(Integer int1) {
	    filme = new Filme();
	    filme.setEstoque(int1);
	}

	@Dado("que o preço do aluguel seja R$ {int}")
	public void queOPreçoDoAluguelSejaR$(Integer int1) {
	    filme.setAluguel(int1);
	}

	@Quando("alugar")
	public void alugar() {
	    notaAluguel = aluguel.alugar(filme);
	}

	@Então("o preço do aluguel será R$ {int}")
	public void oPreçoDoAluguelSeráR$(Integer int1) {
	    Assert.assertEquals(int1.intValue(), notaAluguel.getPreco());
	}

	@Então("a data de entrega será no dia seguinte")
	public void aDataDeEntregaSeráNoDiaSeguinte() {
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DAY_OF_MONTH, 1);
	    
	    Date dataRetorno = notaAluguel.getDataEntrega();
	    Calendar calRetorno = Calendar.getInstance();
	    calRetorno.setTime(dataRetorno);
	    
	    Assert.assertEquals(cal.get(Calendar.DAY_OF_MONTH), calRetorno.get(Calendar.DAY_OF_MONTH));
	    Assert.assertEquals(cal.get(Calendar.MONTH), calRetorno.get(Calendar.MONTH));
	    Assert.assertEquals(cal.get(Calendar.YEAR), calRetorno.get(Calendar.YEAR));
	}

	@Então("o estoque do filme terá {int} unidade")
	public void oEstoqueDoFilmeTeráUnidade(Integer int1) {
	    Assert.assertEquals(int1.intValue(), filme.getEstoque());
	}
}
