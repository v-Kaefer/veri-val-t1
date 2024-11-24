# Mapeamento de Testes para a Jornada do OpenMRS

## Introdução

A jornada reflete cenários reais de uso no sistema, abrangendo desde o cadastro de pacientes até o gerenciamento de ordens e conceitos médicos. Os testes são categorizados por tipos (**TDD**, **CRUD**, **API REST**, **Mocking**) e apresentados conforme os passos da jornada.

---

## Jornada de Usuário

### Passo 1: Cadastrar um Novo Paciente

**Testes Relacionados:**

- **TDD:** Nenhuma evidência clara.
- **CRUD:**
  - `PatientServiceTest.shouldCreatePatient` - **Unitário**: Valida a criação de um paciente.
  - `PatientServiceTest.shouldRetrievePatientById` - **Unitário**: Garante a recuperação do paciente pelo ID.
- **API REST:**
  - `PatientRestControllerTest.testCreatePatient` - **Teste REST**: Valida o endpoint `POST /api/patient` para criar pacientes.
- **Mocking:**
  - `PatientServiceTest.testMockedPatientSave` - **Mocking**: Simula a lógica do repositório para salvar pacientes sem conexão real ao banco.

---

### Passo 2: Adicionar um Novo Conceito Médico

**Testes Relacionados:**

- **TDD:** Evidência clara.
  - `ConceptServiceTest.shouldCreateConceptWithShortName` - **TDD/Unitário**: Valida a criação de conceitos médicos com nomes curtos.
  - `ConceptServiceTest.shouldValidateUniqueNames` - **TDD/Validação**: Garante que conceitos com nomes duplicados não podem ser criados.
- **CRUD:**
  - `ConceptServiceTest.shouldCreateConceptWithShortName` - **Unitário**: Valida a criação de conceitos médicos com nomes curtos.
  - `ConceptServiceTest.shouldValidateUniqueNames` - **Validação**: Garante que conceitos com nomes duplicados não podem ser criados.
- **API REST:**
  - `ConceptRestControllerTest.testCreateConcept` - **Teste REST**: Valida o endpoint `POST /api/concept`.
- **Mocking:**
  - `ConceptServiceTest.testMockedConceptValidation` - **Mocking**: Simula validações de conceitos, como checagem de nomes duplicados.

---

### Passo 3: Criar uma Ordem de Teste

**Testes Relacionados:**

- **TDD:** Nenhuma evidência clara.
- **CRUD:**
  - `OrderServiceTest.shouldCreateOrder` - **Unitário**: Valida a criação de uma ordem médica.
  - `OrderServiceTest.shouldRetrieveOrderById` - **Unitário**: Garante que ordens sejam recuperadas pelo ID.
- **API REST:**
  - `OrderRestControllerTest.testCreateOrder` - **Teste REST**: Valida o endpoint `POST /api/order`.
- **Mocking:**
  - `OrderServiceTest.testMockedOrderSave` - **Mocking**: Simula o salvamento de ordens no repositório.

---

### Passo 4: Consulta de Informações do Paciente

**Testes Relacionados:**

- **TDD:** Nenhuma evidência clara.
- **CRUD:**
  - `PatientServiceTest.shouldRetrievePatientWithEncounters` - **Integração**: Garante que pacientes sejam retornados com os encontros associados.
- **API REST:**
  - `PatientRestControllerTest.testGetPatient` - **Teste REST**: Valida o endpoint `GET /api/patient/{id}`.
- **Mocking:**
  - `PatientServiceTest.testMockedPatientRetrieval` - **Mocking**: Simula a recuperação de pacientes e associações sem acessar a base real.

---

### Passo 5: Validação de Dados

**Testes Relacionados:**

- **TDD:** Nenhuma evidência clara.
- **CRUD:**
  - `ConceptServiceTest.shouldValidateUniqueNames` - **Validação**: Verifica que conceitos possuem nomes únicos.
- **API REST:**
  - `ConceptRestControllerTest.testValidationError` - **Teste REST**: Valida respostas de erro para entradas inválidas.
- **Mocking:**
  - `OrderServiceTest.testMockedOrderValidation` - **Mocking**: Simula validações para ordens médicas.

---

### Passo 6: Gerenciamento de Status

**Testes Relacionados:**

- **TDD:** Nenhuma evidência clara.
- **CRUD:**
  - `OrderServiceTest.shouldUpdateOrderStatus` - **Unitário**: Garante a atualização do status de ordens médicas.
- **API REST:**
  - `OrderRestControllerTest.testUpdateOrderStatus` - **Teste REST**: Valida o endpoint `PUT /api/order/{id}/status`.
- **Mocking:**
  - `OrderServiceTest.testMockedStatusUpdate` - **Mocking**: Simula mudanças de status sem interações reais com dependências externas.

---

## Resumo Consolidado

| **Passo**               | **Testes Identificados**                                                               | **Tipos**                    |
| ----------------------- | -------------------------------------------------------------------------------------- | ---------------------------- |
| Cadastro de Pacientes   | `shouldCreatePatient`, `testCreatePatient`, `testMockedPatientSave`                    | Unitário, REST, Mocking      |
| Adicionar Conceitos     | `shouldCreateConceptWithShortName`, `testCreateConcept`, `testMockedConceptValidation` | TDD, Unitário, REST, Mocking |
| Criar Ordens            | `shouldCreateOrder`, `testCreateOrder`, `testMockedOrderSave`                          | Unitário, REST, Mocking      |
| Consulta de Informações | `shouldRetrievePatientWithEncounters`, `testGetPatient`, `testMockedPatientRetrieval`  | Integração, REST, Mocking    |
| Validação de Dados      | `shouldValidateUniqueNames`, `testValidationError`, `testMockedOrderValidation`        | Validação, REST, Mocking     |
| Gerenciamento de Status | `shouldUpdateOrderStatus`, `testUpdateOrderStatus`, `testMockedStatusUpdate`           | Unitário, REST, Mocking      |

---

## Conclusão

O mapeamento evidencia uma cobertura significativa para a jornada do usuário, destacando testes **CRUD**, **Mocking**, e **APIs REST**. No entanto, recomenda-se:

1. **Expandir TDD:** Implementar testes antes do desenvolvimento de funcionalidades críticas.
2. **Aprimorar Mocking:** Garantir simulações robustas para cenários complexos.
3. **Adicionar Testes de Sistema:** Abranger fluxos completos de ponta a ponta.
