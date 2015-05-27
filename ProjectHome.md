Para enquadrar o trabalho na disciplina de Paradigmas de Programação, os requisitos do sistema foram adaptados e se resumem a:

O aplicativo deve permitir o cadastro de 3 tipos de oportunidades: bolsa, estágio ou emprego. Para todos os tipos de oportunidade, devem ser cadastrados:
um título da oportunidade (p.ex. "Estágio para Desenvolvimento em Java", "Vaga para Bolsista no Projeto XY";
o local de desenvolvimento da atividade (p.ex. nome da empresa, grupo de pesquisa, etc.);
uma descrição da oportunidade (texto livre descrevendo atividades e requisitos);
dados para contato (p.ex. e-mail, página de inscrição, etc);
a carga horária semanal;
valor da bolsa/remuneração.
Dependendo do tipo de oportunidade, há dados adicionais que devem ser cadastrados:
No caso de bolsas, deve ser cadastrado também o nome e o endereço do currículo Lattes do orientador, além da duração (em meses).
No caso de estágio, deve ser cadastrado o órgão responsável pelo contrato de estágio (p.ex. CIEE, ABRH, etc.).
No caso de empregos, devem ser cadastrados os tipos de benefícios oferecidos (vale transporte, alimentação, plano de saúde, etc.).
Por "cadastro", entende-se as operações de: inclusão, leitura, alteração e exclusão (também conhecidas como CRUD - Create/Read/Update/Delete). Além disso, para melhor isualização dos dados, o sistema deve mostrar uma tabela de todas as oportunidades cadastradas.
O aplicativo também deve permitir 2 tipos de buscas:
por tipo de oportunidade (bolsa, estágio ou emprego);
por título e descrição da oportunidade (neste caso, o usuário fornece uma ou mais palavras-chave a serem buscadas no texto do título e da descrição).
Os dados devem ser persistidos em arquivo ou banco de dados, para que permaneçam disponíveis quando o aplicativo for fechado e reaberto.