# Casos Identificados de TDD no OpenMRS Core

Esta é uma lista de commits identificados como potenciais exemplos de práticas de Desenvolvimento Orientado a Testes (TDD) no repositório OpenMRS Core. Cada caso está associado ao tipo de teste e inclui informações sobre o commit, mensagem e arquivo envolvido.

---

## Unit Tests

1. **Commit ID:** `ef8e37c68e122c11aa65e182da9ddd5e972757c5`

   - **Mensagem:** "TRUNK-3253: Add junit test for searching on voided patients."
   - **Por que TDD?** Introduz testes para busca em pacientes anulados antes de expandir funcionalidade.
   - **Arquivo:** `api/src/test/java/org/openmrs/PatientServiceTest.java`

2. **Commit ID:** `501281ee2aee749fcb9b2e5b7101d8912a123b16`
   - **Mensagem:** "TRUNK-4830 add missing junit dependency and fix unit tests in module liquibase."
   - **Por que TDD?** Prepara dependências e corrige testes antes de alterações no módulo.
   - **Arquivo:** `api/src/test/java/org/openmrs/module/LiquibaseTest.java`

---

## Integration Tests

1. **Commit ID:** `fea9d7c1804444f65f2be698cf363b69624e71dd`

   - **Mensagem:** "TRUNK-6275: Adding MySQL and Postgres support to ValidateHibernateMappingsDatabaseIT test."
   - **Por que TDD?** Adiciona suporte para novos bancos no teste antes de adaptar funcionalidades.
   - **Arquivo:** `api/src/test/java/org/openmrs/ValidateHibernateMappingsDatabaseIT.java`

2. **Commit ID:** `e836d7a74e1650aa6da60851daa45bc4b9b960b8`
   - **Mensagem:** "TRUNK-6028 add postgres support to base test class."
   - **Por que TDD?** Adapta testes básicos para garantir suporte a PostgreSQL antes de alterações.
   - **Arquivo:** `api/src/test/java/org/openmrs/BaseTestClass.java`

---

## System Tests

1. **Commit ID:** `4ef57113a4751510d7a9fcc0fa8ebf65df5e5923`
   - **Mensagem:** "TRUNK-5490: Add DTD config tests."
   - **Por que TDD?** Introduz testes para configurações DTD antes de alterações de funcionalidade.
   - **Arquivo:** `webapp/src/test/java/org/openmrs/web/DTDConfigTest.java`

---

## Performance Tests

1. **Commit ID:** `3a8d71767ace844f4db598856ffa9e2f532836b1`
   - **Mensagem:** "TRUNK-425 Added performance test."
   - **Por que TDD?** Introduz um teste de desempenho para monitorar melhorias em fases iniciais.
   - **Arquivo:** `api/src/test/java/org/openmrs/PerformanceTest.java`

---

## Validation Tests

1. **Commit ID:** `b2835786fbe9588c130e6d5289499ea985da0ecd`
   - **Mensagem:** "TRUNK-5838: Correct version validate method and add test to check that."
   - **Por que TDD?** Testa validação de versões antes de refatorar o método.
   - **Arquivo:** `api/src/test/java/org/openmrs/ValidationTest.java`

---

## Functional Tests

1. **Commit ID:** `18c9c2a4c5a922c56aab240cdf9401441ff1b3ce`
   - **Mensagem:** "TRUNK-5816 Add JUnit 4 tests."
   - **Por que TDD?** Introduz testes para guiar mudanças antes de implementar lógica dependente.
   - **Arquivo:** `api/src/test/java/org/openmrs/FunctionalTest.java`

---

## Outros

1. **Commit ID:** `df3b2c17e97529ae88c98a693e74ade180df1f55`
   - **Mensagem:** "TRUNK-5064: Added untested methods for PersonServiceImpl."
   - **Por que TDD?** Prepara testes e adaptações antes de funcionalidade.
   - **Arquivo:** `api/src/main/java/org/openmrs/api/impl/PersonServiceImpl.java`

---
