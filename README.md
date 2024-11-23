# Roteiro da apresentação (+-10min):

* Usar um slide para apresentar o nome dos integrantes;
* Apresentar o sistema escolhido;
* Apresentar o resultado da análise dos testes existentes;
* Apresentar a estrutura de exemplo de teste unitário, de integração e de sistema (não precisa executar os testes).


Roteiro de Apresentação (10 minutos)
## 1. Slide Inicial – Apresentação dos Integrantes
Título: "Análise de Testes no OpenMRS Core"
Nome dos integrantes.
Pequeno logo ou imagem ilustrativa do OpenMRS (opcional).
## 2. Introdução ao Sistema Escolhido – OpenMRS Core
O que é o OpenMRS?

Um sistema de código aberto focado no gerenciamento de informações de saúde, utilizado principalmente em países em desenvolvimento.
Importância do OpenMRS como solução modular e extensível para instituições médicas.
OpenMRS Core:

É o núcleo do sistema, fornecendo funcionalidades centrais como gerenciamento de pacientes, registros médicos e suporte à integração com módulos externos.
Tecnologias utilizadas:

Linguagem: Java.
Frameworks principais: Spring, Hibernate.
Gestão de dependências: Maven.

## 3. Análise dos Testes Existentes (4 min)
#### a) Tipos de Testes Encontrados
Os testes no OpenMRS Core são amplos e seguem uma estrutura organizada para garantir a qualidade de diferentes camadas do sistema:

### Testes Unitários:

Com foco em métodos ou classes individuais, garantindo que uma funcionalidade isolada opere corretamente.
Frequentes em pacotes como org.openmrs.api (Serviços principais) e org.openmrs.util (utilitários).
Utilizam mocking extensivamente com ferramentas como Mockito, para simular dependências e isolar as unidades testadas.
### Testes de Integração:

Avaliam interações entre componentes, como serviços Spring e operações de banco de dados com Hibernate.
Incluem testes de persistência e transações para garantir a integridade dos dados em operações complexas.
Dependem de bancos de dados em memória (como H2) configurados para simular os ambientes reais.
### Testes de Sistema (ou E2E):

Testam fluxos completos de funcionalidades do usuário final, como o registro de pacientes e atualizações nos registros médicos.
Usam ferramentas externas para automação de UI em casos mais avançados, embora sejam menos comuns no repositório principal do OpenMRS Core.

## b) Cobertura dos Testes
### Níveis de Cobertura:

O OpenMRS Core apresenta uma cobertura de testes razoável em áreas críticas como:
Serviços de Pacientes (PatientService): Testes garantem a criação, leitura, atualização e exclusão de registros.
Autenticação e Controle de Acesso (UserService): Cobertura robusta para fluxos de login, permissões e gerenciamento de usuários.
Registros Médicos (EncounterService): Cobertura significativa em operações de registro e manipulação de dados médicos.
Contudo, há lacunas em áreas menos críticas ou mais complexas, como APIs REST para operações específicas e módulos complementares.
### Métricas de Cobertura:

A cobertura do código é frequentemente verificada por ferramentas como JaCoCo, que avalia a execução de linhas de código e ramificações durante os testes.
Algumas partes do código apresentam cobertura superior a 80%, especialmente nos módulos mais estáveis e usados em produção.
## c) Observações Detalhadas
### Uso de Simulações (Mocking):

Prática: Mocking é amplamente usado para simular comportamentos de componentes dependentes, como conexões de banco de dados ou respostas de APIs externas.
- Benefício: Isso acelera os testes e reduz a necessidade de infraestrutura complexa durante a execução.
- Desafio: Excessivo uso de mocks pode limitar a verificação de cenários reais, especialmente nos testes de integração.
### Dependência de Bancos de Dados em Memória:

Testes de integração são configurados para usar o banco H2 (em memória), replicando esquemas reais definidos em Hibernate.
- Vantagem: Permite uma execução rápida e controlada.
- Desvantagem: Podem surgir inconsistências ao migrar para bancos de produção (MySQL, PostgreSQL).
### Cobertura Modular:

O OpenMRS Core é altamente modular, com dependências gerenciadas por Maven.
Nem todos os módulos são igualmente testados, e módulos externos (add-ons) frequentemente têm menor cobertura.
Testes para APIs REST:

Testes para endpoints REST estão incluídos, mas não cobrem todos os cenários possíveis.
Algumas APIs REST, como as de integração de dados com sistemas de terceiros, são testadas apenas parcialmente, possivelmente devido à sua complexidade.
### Estratégia de Manutenção:

A comunidade OpenMRS realiza revisões regulares para melhorar a qualidade dos testes e abordar lacunas identificadas.
Pull requests frequentemente incluem casos de teste como requisito obrigatório antes da aprovação.
### Desafios Identificados:

- Complexidade: Testar cenários que envolvem múltiplos módulos pode ser desafiador devido às dependências cruzadas.
- Cobertura de Casos Extremos: Nem todos os testes lidam bem com situações atípicas, como dados corrompidos ou entradas incompletas.
- Evolução do Código: A evolução constante do OpenMRS Core pode introduzir regressões em partes do sistema que não possuem testes robustos.
### Pontos Positivos:

A estrutura modular e a separação de responsabilidades permitem um desenvolvimento e teste mais focado.
A comunidade mantém um bom histórico de correção de erros reportados através de testes regressivos.


## 4. Tipos de Testes e Abordagens de Desenvolvimento (3 min)
#### a) TDD (Test-Driven Development) no OpenMRS Core
Descrição:
Abordagem onde os testes são escritos antes do código principal.
Utilizada em módulos críticos, como serviços do núcleo (Service Layer), para garantir que os métodos atendam aos requisitos desde o início.
Exemplo de fluxo:
Escrever o teste.
Executar o teste e falhar.
Implementar a funcionalidade.
Validar o teste e refatorar o código.
#### b) CRUD e a Estrutura das APIs
Descrição:
APIs no OpenMRS Core frequentemente seguem operações CRUD (Create, Read, Update, Delete) para gerenciar dados de pacientes, usuários e registros médicos.
Testes CRUD garantem que cada operação funcione conforme esperado, considerando cenários normais e de falha.
Exemplo de teste CRUD para serviço de pacientes:
java

@Test
public void createAndRetrievePatient_shouldWorkCorrectly() {
    // Create
    Patient patient = new Patient();
    patient.setName("John Doe");
    patientService.savePatient(patient);
    
    // Retrieve
    Patient retrievedPatient = patientService.getPatient(patient.getId());
    assertEquals("John Doe", retrievedPatient.getName());
}
#### c) Testes de APIs REST
Descrição:
O OpenMRS oferece APIs REST para integração com outros sistemas.
Testes garantem que os endpoints retornem os dados corretos e lidem adequadamente com erros.
Exemplo de teste para um endpoint REST:
java
Copiar código
@Test
public void testGetPatientEndpoint() {
    Response response = given()
        .when()
        .get("/api/patient/1")
        .then()
        .statusCode(200)
        .extract().response();
    
    assertEquals("John Doe", response.jsonPath().getString("name"));
}
#### d) Mocking e Simulação
Descrição:
Dependências externas, como conexões com banco de dados ou APIs de terceiros, são simuladas usando frameworks como Mockito.
Exemplo com Mockito:
java

@Test
public void testMockedPatientService() {
    PatientService mockService = Mockito.mock(PatientService.class);
    Patient mockPatient = new Patient();
    mockPatient.setName("Jane Doe");
    
    Mockito.when(mockService.getPatient(1)).thenReturn(mockPatient);
    
    Patient result = mockService.getPatient(1);
    assertEquals("Jane Doe", result.getName());
}

## 5. Estruturas de Exemplos de Testes (2 min)
#### a) Teste Unitário
Descrição: Testa um método isolado.
Exemplo:
java
Copiar código
@Test
public void getPatient_shouldReturnPatientWithGivenId() {
    Patient patient = Context.getPatientService().getPatient(1);
    assertNotNull(patient);
    assertEquals(1, patient.getId());
}
#### b) Teste de Integração
Descrição: Valida interações entre componentes.
Exemplo:
java
Copiar código
@SpringBootTest
public class UserIntegrationTest {
    @Test
    public void shouldSaveAndRetrieveUser() { /*...*/ }
}
#### c) Teste de Sistema
Descrição: Simula um fluxo completo.
Exemplo:
java
Copiar código
@Test
public void shouldRegisterPatient() { /*...*/ }

## 6. Conclusão (30s)
Resumo:
OpenMRS Core é um exemplo de sistema bem estruturado, com boas práticas de testes.
Aplicação de abordagens como TDD e suporte a APIs CRUD demonstra o foco na confiabilidade e na integração.
Perguntas e Comentários.