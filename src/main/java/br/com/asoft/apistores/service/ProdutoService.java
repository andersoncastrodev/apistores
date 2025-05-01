package br.com.asoft.apistores.service;

import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.model.Product;
import br.com.asoft.apistores.model.Supplier;
import br.com.asoft.apistores.relatorio.Reports;
import br.com.asoft.apistores.respository.ProdutoRepository;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    private final FornecedorService fornecedorService;

    public Page<Product> allTodosPage(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    public List<Product> allTodos(){
        return produtoRepository.findAll();
    }

    public Product findId(Long id){
        return tryOrFail(id);
    }

    public Product salvaProduto(Product product){

        Long fornecedorId = product.getSupplier().getId();

        Supplier supplier = fornecedorService.tryOrFail(fornecedorId);

        product.setSupplier(supplier);

        return produtoRepository.save(product);
    }

    public void deletarProduto(Long id){

        Product product = findId(id);

        produtoRepository.delete(product);
        produtoRepository.flush();
    }

    public Product tryOrFail(Long id){
        return produtoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundExceptions("Product",id));
    }

    public ByteArrayInputStream relatorioTodosProdutos() throws IOException {

        Reports reports = new Reports(Reports.Page.VERTICAL);

        reports.addParagraph( new Paragraph("Lista de Produtos")
                .setMargins(1f,5f,1f,5f)
                .setFontSize(28)
                .setTextAlignment(TextAlignment.CENTER)
                .setFont(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD)));

        reports.addNewLine();

        reports.openTable(1f,1f,1f,1f,1f);

        reports.addTableHeader("Codigo","Descrição","Valor Comprar","Valor Sales","Supplier");

        List<Product> products = allTodos();

        for (Product product : products) {

            reports.addCellCenter(product.getId());
            reports.addCellCenter(product.getDescricao());
            reports.addCellCenter(product.getValorCompra());
            reports.addCellCenter(product.getValorVenda());
            reports.addCellCenter(product.getSupplier().getNome());
        }

        reports.closeTable();
        reports.closeDocument();

        return reports.getByteArrayInputStream();

    }



}
