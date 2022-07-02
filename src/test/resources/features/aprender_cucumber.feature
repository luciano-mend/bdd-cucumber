# language: pt
Funcionalidade: Aprender Cucumber
	Como um aluno
	Eu quero aprender a utilizar Cucumber
	Para que eu possa automatizar criterios de aceitacao

Cenario: Deve executar especificacao
	Dado que criei o arquivo corretamente
	Quando executá-lo
	Entao a especificacao deve finalizar com sucesso
	
Cenario: Deve incrementar contador
	Dado que o valor do contador e 15
	Quando eu incrementar em 3
	Entao o valor do contador sera 18
	
Cenario: Deve incrementar contador
	Dado que o valor do contador e 123
	Quando eu incrementar em 35
	Entao o valor do contador sera 158

@esse
Cenario: Deve calcular atraso na entrega
	Dado que o prazo e dia 05/04/2022
	Quando a entrega atrasar em 2 dias
	Entao a entrega sera efetuada em 07/04/2022
	
Cenario: Deve calcular atraso no prazo de entrega da China
	Dado que o prazo e dia 05/04/2022
	Quando a entrega atrasar em 2 meses
	Entao a entrega sera efetuada em 05/06/2022
	
Cenário: Deve criar steps genericos para estes passos
    Dado que o ticket é AF345
    Dado que o valor da passagem e R$ 230,45
    Dado que o nome do passageiro e "Fulano da Silva"
    Dado que o telefone do passageiro e 9999-9999
    Quando criar os steps
    Entao o teste vai funcionar

Cenario: Deve reaproveitar os steps "Dado" do cenario anterior
    Dado que o ticket é AB167
    Dado que o ticket especial é AB167
    Dado que o valor da passagem e R$ 1120,23
    Dado que o nome do passageiro e "Cicrano de Oliveira"
    Dado que o telefone do passageiro e 9888-8888

Cenario: Deve negar todos os steps "Dado" dos cenarios anteriores
    Dado que o ticket e CD123
    Dado que o ticket e AG1234
    Dado que o valor da passagem e R$ 1.1345,56
    Dado que o nome do passageiro e "Beltrano Souza Matos de Alcântara Azevedo"
    Dado que o telefone do passageiro e 1234-5678
    Dado que o telefone do passageiro e 999-2223

