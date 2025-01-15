alter table registroPreguntas add activo tinyint;

update registroPreguntas set activo = 1