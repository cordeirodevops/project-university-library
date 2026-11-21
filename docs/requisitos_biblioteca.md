Instituto Infnet

Engenharia de Software

Projeto de Bloco: Desenvolvimento Back-End

Professor Orlando Fonseca Guilarte

Discente Carlos A C Oliveira

# Sistema de Gestão de Biblioteca Universitária
**Documento de Requisitos, Casos de Uso e Escopo**

---

## 1. Documento de Requisitos

### Requisitos Funcionais (RF)
- **RF01 - Manter Acervo:** O sistema deve permitir que o Bibliotecário cadastre, consulte, atualize e remova registros de Livros (título, autor, ISBN) e seus Exemplares (código de patrimônio, estado de conservação, disponibilidade).
- **RF02 - Manter Membros:** O sistema deve permitir que o Bibliotecário gerencie o cadastro de Membros (nome, matrícula, tipo: aluno, professor, funcionário).
- **RF03 - Registrar Empréstimo:** O sistema deve registrar empréstimos, vinculando um exemplar disponível a um membro válido, definindo o prazo de devolução para 14 dias corridos.
- **RF04 - Validar Regras de Empréstimo:** O sistema deve impedir que um membro exceda 3 empréstimos ativos simultâneos e garantir que o exemplar esteja em condições de uso.
- **RF05 - Registrar Devolução:** O sistema deve encerrar empréstimos ativos mediante a devolução do exemplar, registrando a data efetiva.
- **RF06 - Calcular e Registrar Multa:** Caso a devolução ocorra após o prazo, o sistema deve calcular automaticamente a multa proporcional aos dias de atraso e vinculá-la ao membro.
- **RF07 - Registrar Reserva:** O sistema deve permitir que um membro reserve um livro (e não um exemplar específico) apenas se todos os exemplares físicos daquele título estiverem atualmente emprestados.

### Requisitos Não Funcionais (RNF)
- **RNF01 - Arquitetura de Transição:** O sistema deve ser desenvolvido inicialmente como uma aplicação Java standalone de forma procedural, evoluindo progressivamente para o paradigma Orientado a Objetos e, posteriormente, para uma API RESTful.
- **RNF02 - Persistência Temporária:** Na etapa inicial, os dados serão mantidos apenas em memória (estruturas de dados primitivas/arrays), sem a utilização de banco de dados.

---

## 2. Especificação de Casos de Uso (UML)

### Atores
- **Membro:** Aluno, professor ou funcionário que consulta o acervo, realiza empréstimos, devoluções e reservas.
- **Bibliotecário/Administrador:** Responsável por gerenciar os cadastros e processar as operações administrativas do sistema.

### Cenários Principais

#### Cenário 1: Realizar Empréstimo
- **Ator:** Membro, Bibliotecário
- **Fluxo Principal:** 
  1. O Membro solicita o empréstimo de um livro.
  2. O Bibliotecário localiza o cadastro do Membro e o Exemplar no sistema.
  3. O sistema valida que o Membro tem menos de 3 empréstimos e que o Exemplar está disponível.
  4. O sistema registra o empréstimo com a data atual e define o prazo para 14 dias.
- **Fluxos Alternativos:**
  - *Falha na validação do Membro:* O sistema acusa que o Membro já possui 3 empréstimos ativos e bloqueia a operação.
  - *Falha na validação do Exemplar:* O sistema acusa que o exemplar está danificado ou já emprestado, bloqueando a operação.

#### Cenário 2: Realizar Devolução
- **Ator:** Membro, Bibliotecário
- **Fluxo Principal:**
  1. O Membro entrega o exemplar.
  2. O Bibliotecário insere o código do exemplar no sistema.
  3. O sistema localiza o empréstimo ativo e registra a data efetiva de devolução dentro do prazo.
  4. O status do exemplar volta para "disponível".
- **Fluxos Alternativos:**
  - *Devolução em atraso:* O sistema identifica que a data efetiva é posterior ao prazo limite. Calcula a multa (dias de atraso), registra a pendência no perfil do Membro e encerra o empréstimo.

#### Cenário 3: Solicitar Reserva
- **Ator:** Membro
- **Fluxo Principal:**
  1. O Membro busca por um livro específico e deseja reservá-lo.
  2. O sistema verifica o estoque e confirma que todos os exemplares estão emprestados.
  3. O sistema registra a reserva vinculada ao perfil do Membro e ao Livro.
- **Fluxos Alternativos:**
  - *Exemplar disponível:* O sistema identifica que há pelo menos um exemplar físico na biblioteca e bloqueia a reserva, orientando o Membro a buscar o exemplar na estante.

---

## 3. Justificativa de Escopo (Fase Inicial)

Para esta primeira etapa da trilha de desenvolvimento, o escopo foi delimitado propositalmente para focar na **compreensão do problema e na estruturação lógica**. 

**O que está dentro do escopo:**
- O mapeamento completo das regras de negócio em requisitos.
- A modelagem dos fluxos de interação entre usuário e sistema (Casos de Uso).
- A criação de um programa executável inicial (esqueleto) para validar o ambiente e exercitar lógica básica (estruturas de controle, sem orientação a objetos).

**O que foi deixado para as próximas etapas:**
- Estruturas de Classes, Interfaces e Herança (fase Orientação a Objetos).
- Banco de dados relacional (fase de Persistência).
- Interface web, API e Spring Boot (fase de Infraestrutura e Serviço).
- Pagamento de multas e bloqueio de novos empréstimos por inadimplência.

---

## 4. Diagrama de Casos de Uso

```mermaid
flowchart LR
    %% Atores
    Membro((Membro))
    Biblio((Bibliotecário))

    %% Casos de Uso
    Emprestimo([Realizar Empréstimo])
    Devolucao([Realizar Devolução])
    Reserva([Solicitar Reserva])

    %% Relações
    Membro --> Emprestimo
    Membro --> Devolucao
    Membro --> Reserva

    Biblio --> Emprestimo
    Biblio --> Devolucao
