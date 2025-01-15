create table registroPreguntas(

    id bigint not null auto_increment,
    idUsuario varchar(6) not null,
    NombreUsuario varchar(100) not null unique,
    Titulo varchar(100) not null,
    Mensaje varchar(300) not null,
    Respuesta varchar(300) not null,

    primary key(id)


);