CREATE  TABLE cliente (
    id LONG NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    telefone LONG  NOT NULL,
    correntista boolean not null,
    saldo_cc float not null,
    PRIMARY KEY (id)
    );