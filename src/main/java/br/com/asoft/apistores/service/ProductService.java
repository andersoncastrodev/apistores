package br.com.asoft.apistores.service;

import br.com.asoft.apistores.dto.ProductDto;
import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.filter.ProductFilter;
import br.com.asoft.apistores.mapper.ProductMapper;
import br.com.asoft.apistores.model.Category;
import br.com.asoft.apistores.model.Product;
import br.com.asoft.apistores.model.Supplier;
import br.com.asoft.apistores.respository.ProductRepository;
import br.com.asoft.apistores.specifications.ProductSpec;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final SuppilerService suppilerService;
    private final CategoryService categoryService;
    private final ProductMapper productMapper;

    public Page<ProductDto> allProductPage(ProductFilter productFilter, Pageable pageable) {
        Page<Product> product = productRepository.findAll(ProductSpec.filter(productFilter), pageable);
        List<ProductDto> listProductDto = productMapper.toListProductDto(product.getContent());
        Page<ProductDto> productDtoPage = new PageImpl<>(listProductDto, pageable, product.getTotalElements());
        return productDtoPage;
    }

    @Transactional
    public ProductDto createProduct(ProductDto productDto) {
        //Busca o fornecedor e a categoria. O objeto mesmo pelo ID.
        Supplier supplier = suppilerService.tryOrFail(productDto.getSupplierId());
        Category category = categoryService.tryOrFail(productDto.getCategoryId());

        Product product = productMapper.toProduct(productDto);

        product.setSupplier(supplier); //setando o fornecedor
        product.setCategory(category); //setando a categoria

        return productMapper.toProductDto( productRepository.save(product));
    }

    @Transactional
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        //Busca o fornecedor e a categoria. O objeto mesmo pelo ID.
        Supplier supplier = suppilerService.tryOrFail(productDto.getSupplierId());
        Category category = categoryService.tryOrFail(productDto.getCategoryId());

        Product product = tryOrFail(id);

        productMapper.copyToProduct(productDto, product);

        product.setSupplier(supplier); //setando o fornecedor
        product.setCategory(category); //setando a categoria

        return productMapper.toProductDto(productRepository.save(product));
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = tryOrFail(id);
        productRepository.delete(product);
        productRepository.flush();
    }

    public Product findProductId(Long id){
        return tryOrFail(id);
    }

    public Product tryOrFail(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundExceptions("Product not found",id));
    }
}
