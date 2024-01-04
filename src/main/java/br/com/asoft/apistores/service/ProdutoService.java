package br.com.asoft.apistores.service;

import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.model.Fornecedor;
import br.com.asoft.apistores.model.Produto;
import br.com.asoft.apistores.relatorio.Reports;
import br.com.asoft.apistores.respository.ProdutoRepository;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    private final FornecedorService fornecedorService;

    public List<Produto> allTodos(){
        return produtoRepository.findAll();
    }

    public Produto findId(Long id){
        return tryOrFail(id);
    }

    public Produto salvaProduto(Produto produto){

        Long fornecedorId = produto.getFornecedor().getId();

        Fornecedor fornecedor = fornecedorService.tryOrFail(fornecedorId);

        produto.setFornecedor(fornecedor);

        return produtoRepository.save(produto);
    }

    public void deletarProduto(Long id){

        Produto produto = findId(id);

        produtoRepository.delete(produto);
        produtoRepository.flush();
    }

    public Produto tryOrFail(Long id){
        return produtoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundExceptions("Produto",id));
    }

    public ByteArrayInputStream relatorioTodosProdutos() throws IOException {

        Reports reports = new Reports(false);

        reports.addParagraph( new Paragraph("Lista de Produtos")
                .setMargins(1f,5f,1f,5f)
                .setFontSize(28)
                .setTextAlignment(TextAlignment.CENTER)
                .setFont(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD)));

        reports.addNewLine();

        reports.openTable(1f,1f,1f,1f,1f);

        reports.addTableHeader("Codigo","Descrição","Valor Comprar","Valor Venda","Fornecedor");

        List<Produto> produtos = allTodos();

        for (Produto produto : produtos ) {

            reports.addCellCenter(produto.getId());
            reports.addCellCenter(produto.getDescricao());
            reports.addCellCenter(produto.getValorCompra());
            reports.addCellCenter(produto.getValorVenda());
            reports.addCellCenter(produto.getFornecedor().getNome());
        }

        reports.closeTable();
        reports.closeDocument();

        return reports.getByteArrayInputStream();

    }



}
