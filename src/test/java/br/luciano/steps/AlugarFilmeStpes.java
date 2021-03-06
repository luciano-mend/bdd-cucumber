package br.luciano.steps;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.junit.Assert;

import br.luciano.entidades.Filme;
import br.luciano.entidades.NotaAluguel;
import br.luciano.entidades.TipoAluguel;
import br.luciano.servicos.AluguelService;
import br.luciano.utils.DateUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class AlugarFilmeStpes {

	private Filme filme;
	private AluguelService aluguel = new AluguelService();
	private NotaAluguel notaAluguel;
	private String erro;
	private TipoAluguel tipoAluguel;
	
	@Dado("um filme com estoque de {int} unidades")
	public void umFillmeComEstoqueDeUnidades(Integer int1) {
	    filme = new Filme();
	    filme.setEstoque(int1);
	}

	@Dado("que o preço do aluguel seja R$ {int}")
	public void queOPreçoDoAluguelSejaR$(Integer int1) {
	    filme.setAluguel(int1);
	}
	
	@Dado("um filme")
	public void umFilme(DataTable dataTable) {
		Map<String, String> map = dataTable.asMap(String.class, String.class);
		filme = new Filme();
	    filme.setEstoque(Integer.parseInt(map.get("estoque")));
	    filme.setAluguel(Integer.parseInt(map.get("preco")));
	    String tipo = map.get("tipo");
	    tipoAluguel = tipo.equals("semanal") ?
	    		TipoAluguel.SEMANAL : tipo.equals("extendido") ?
	    				TipoAluguel.EXTENDIDO : TipoAluguel.COMUM;
	}

	@Quando("alugar")
	public void alugar() {
		try {
			notaAluguel = aluguel.alugar(filme, tipoAluguel);
		} catch (RuntimeException e) {
			erro = e.getMessage();
		}
	}

	@Então("o preço do aluguel será R$ {int}")
	public void oPreçoDoAluguelSeráR$(Integer int1) {
	    Assert.assertEquals(int1.intValue(), notaAluguel.getPreco());
	}

	@Então("o estoque do filme será {int} unidade")
	public void oEstoqueDoFilmeTeráUnidade(Integer int1) {
	    Assert.assertEquals(int1.intValue(), filme.getEstoque());
	}
	
	@Então("não será possível por falta de estoque")
	public void nãoSeráPossívelPorFaltaDeEstoque() {
	    Assert.assertEquals("Filme sem estoque", erro);
	}
	
	@Dado("^que o tipo do aluguel seja (.*)$")
	public void queOTipoDoAluguelSejaExtendido(String tipo) {
	    tipoAluguel = tipo.equals("semanal") ? TipoAluguel.SEMANAL : tipo.equals("extendido") ? TipoAluguel.EXTENDIDO : TipoAluguel.COMUM;
	}

	@Então("a data de entrega será em {int} dia(s)")
	public void aDataDeEntregaSeráEmDias(Integer int1) {
	    Date dataExperada = DateUtils.obterDataDiferencaDias(int1);
	    Date dataReal = notaAluguel.getDataEntrega();
	    
	    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	    
	    Assert.assertEquals(format.format(dataExperada), format.format(dataReal));
	}

	@Então("a pontuação recebida será de {int} pontos")
	public void aPontuaçãoRecebidaSeráDePontos(Integer int1) {
	    Assert.assertEquals(int1.intValue(), notaAluguel.getPontuacao());
	}
	
}
