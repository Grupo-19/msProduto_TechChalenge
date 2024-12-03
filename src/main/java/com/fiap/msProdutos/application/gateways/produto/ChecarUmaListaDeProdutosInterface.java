package com.fiap.msProdutos.application.gateways.produto;

import com.fiap.msProdutos.domain.entity.produto.Produto;

import java.util.List;

public interface ChecarUmaListaDeProdutosInterface {

    boolean checarUmaListaDeProdutos(List<Produto> produtos);
}