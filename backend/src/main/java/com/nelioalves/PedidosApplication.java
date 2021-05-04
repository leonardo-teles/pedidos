package com.nelioalves;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nelioalves.domain.Categoria;
import com.nelioalves.domain.Cidade;
import com.nelioalves.domain.Cliente;
import com.nelioalves.domain.Endereco;
import com.nelioalves.domain.Estado;
import com.nelioalves.domain.Produto;
import com.nelioalves.enums.TipoCliente;
import com.nelioalves.repositories.CategoriaRepository;
import com.nelioalves.repositories.CidadeRepository;
import com.nelioalves.repositories.ClienteRepository;
import com.nelioalves.repositories.EnderecoRepository;
import com.nelioalves.repositories.EstadoRepository;
import com.nelioalves.repositories.ProdutoRepository;

@SpringBootApplication
public class PedidosApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	public static void main(String[] args) {
		SpringApplication.run(PedidosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));	
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Rio de Janeiro");
		Estado est2 = new Estado(null, "Minas Gerais");
		
		Cidade c1 = new Cidade(null, "Rio de Janeiro", est1);
		Cidade c2 = new Cidade(null, "Uberlândia", est2);
		Cidade c3 = new Cidade(null, "Nova Iguaçu", est1);
		
		est1.getCidades().addAll(Arrays.asList(c1, c3));
		est2.getCidades().addAll(Arrays.asList(c2));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Leonardo Almeida", "leonardo@gmail.com", "03746549728", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("991054374", "979615072"));
		
		Endereco e1 = new Endereco(null, "Estrada da Cachamorra", "1233", "bloco 11 apto 210", "Campo Grande", "23040150", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua Murilo Alvarenga", "65", "bloco 2 apto 206", "Campo Grande", "22090180", cli1, c1);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
	}
}
