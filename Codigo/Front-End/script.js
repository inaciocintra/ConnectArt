function adicionarProduto(alvo, imagem, nome, descricao, preco, link) {
    var alvo = document.getElementById(alvo);
    if (alvo == null) {
        stop;
    }

    link = "/pesquisa/pesquisa.html"
    var precoFormatado = preco.toFixed(2);
    // Cria um novo elemento div com a classe 'col-6 col-md-4'
    var novoProduto = document.createElement("div");
    novoProduto.classList.add("col-6", "col-md-4");

    // Cria a estrutura interna do novo produto com base no HTML fornecido
    novoProduto.innerHTML = `
        <div class="card m-1 border-0">
            <img src="https://via.placeholder.com/150x100" class="card-img-top rounded shadow" alt="...">
            <div class="card-body">
                <p class="card-text fw-bold m-0">${nome}</p>
                <p class="card-text m-0">${descricao}</p>
                <p class="card-text fw-bold" style="display: inline">R$${precoFormatado}</p>
                <a class="btn btn-sm btn-dark float-end" href="${link}">Conferir</a>
            </div>
        </div>
    `;

    // Adiciona o novo produto ao elemento alvo especificado
    alvo.appendChild(novoProduto);
}

function alterarPopular(alvo, imagem, nome, descriçao, preco, link) {
    link = "/produto/produto.html"
    var precoFormatado = preco.toFixed(2);

    if (alvo) {
        var inner = document.getElementById(alvo + "Dados")
        inner.innerHTML = `
            <h5 class="fw-bold mb-1">${nome}</h5>
            <p class="mb-1">${descriçao}</p>
            <p class="mb-0 fw-bold">R$${precoFormatado}</p>
        `
    }

    if (link != null) {
        var ref = document.getElementById(alvo + "Ref");
        ref.href = link;
    }

    if (imagem != null) {
        var img = document.getElementById(alvo + "Imagem");
        img.src = imagem;
    }
}

// Exemplo de geração dos cards
for (i = 0; i < 6; i++) {
    var numero = Math.floor(Math.random() * 99);
    var preço = Math.random() * 99;
    adicionarProduto("recomendadosCards", null, `Recomendado ${numero}`, "Esse elemento foi gerado utilizado JavaScript com um número aleatório", preço)
}

// Exemplo de alteração dos populares
for (i = 1; i <= 3; i++) {
    var numero = Math.floor(Math.random() * 99);
    var preço = Math.random() * 99;
    alterarPopular(`popular${i}`, null, `Popular ${numero}`, "Elemento gerado aleatoriamente", preço)
}

//----- manda requisição para criar cliente -----
/*function cadastrarCliente() {
    const cliente = {
      
      clienteNome: document.getElementById('clienteNome').value,
      clienteEmail: document.getElementById('clienteEmail').value,
      clienteSenha: document.getElementById('clienteSenha').value,
      
    };

    fetch('http://localhost:4567/cliente', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(cliente)
    }).then(response => response.json())
      .then(data => {
        console.log('Success:', data);
      })
      .catch((error) => {
        console.error('Error:', error);
      }); 
  }*/