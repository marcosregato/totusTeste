CREATE TABLE conta(
	id SERIAL PRIMARY KEY,
	data_vencimento VARCHAR(10),
	data_pagamento VARCHAR(10),
	valor INT,
	descricao VARCHAR(10),
	situacao VARCHAR(10)
);