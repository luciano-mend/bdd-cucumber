package br.luciano.steps;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class AprenderCucumberSteps {

	@Dado("que criei o arquivo corretamente")
	public void queCrieiOArquivoCorretamente() {
	}
	
	@Quando("executá-lo")
	public void executaLo() {
	}
	
	@Entao("a especificacao deve finalizar com sucesso")
	public void aEspecificacaoDeveFinalizarComSucesso() {
	}
	
	private Integer contador = 0;
	
	@Dado("que o valor do contador e {int}")
	public void queOValorDoContadorE(Integer int1) {
		contador = int1;
	}
	@Quando("eu incrementar em {int}")
	public void euIncrementarEm(Integer int1) {
		contador = contador + int1;
	}
	@Entao("o valor do contador sera {int}")
	public void oValorDoContadorSera(Integer int1) {
		Assert.assertEquals(int1, contador);
	}

	
	Date entrega = new Date();
	
//	@Dado("que o prazo e dia {data}")
//	public void queAEntregaEDia(Date data) {
//		entrega = data;
//	}
	
	@Quando("^a entrega atrasar em (\\d+) (dia|dias|mes|meses)$")
	public void aEntregaAtrasarDias(Integer int1, String tempo) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(entrega);
		if (tempo.equals("dias")) {
			cal.add(Calendar.DAY_OF_MONTH, int1);
		}
		if (tempo.equals("meses")) {
			cal.add(Calendar.MONTH, int1);
		}
		entrega = cal.getTime();
	}
	
	@Entao("^a entrega sera efetuada em (\\d{2}\\/\\d{2}\\/\\d{4})$")
	public void aEntregaSeraEfetuadaEm(String data) {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = format.format(entrega);
		Assert.assertEquals(data, dataFormatada);
	}


	@Dado("^que o ticket( especial)? é (AF345|AB167)$")
	public void queOTicketE(String especial, String ticket) {
	}
	
	@Dado("que o valor da passagem e R$ {double}") //(\d+),(\d+)
	public void queOValorDaPassagemER$(Double double1) {
	}
	
	@Dado("que o nome do passageiro e {string}")
	public void queONomeDoPassageiroE(String string) {
	}
	
	@Dado("que o telefone do passageiro e (9\\d{3}-\\d{4})$")
	public void queOTelefoneDoPassageiroE(String telefone) {
	}
	
	@Quando("criar os steps")
	public void criarOsSteps() {
	}
	
	@Entao("o teste vai funcionar")
	public void oTesteVaiFuncionar() {
	}





}
