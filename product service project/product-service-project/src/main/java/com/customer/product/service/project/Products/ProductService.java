package com.customer.product.service.project.Products;


import com.customer.product.service.project.dto.GetAvailabilityDto;
import com.customer.product.service.project.dto.GetProductList;
import com.customer.product.service.project.dto.ListOfProductsDto;
import com.customer.product.service.project.dto.ProductDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ProductService {
    @Autowired
     private ProductRepository productRepository;

    public HashMap<String,Object> getAllProducts(){
        HashMap<String,Object> response = new HashMap<>();
        GetProductList getProductList = new GetProductList();
        List<Product> products = productRepository.findAll();
        List<ListOfProductsDto> listOfProducts=new ArrayList<>();
        for (Product product:products){
            ListOfProductsDto listOfProductsDto =new ListOfProductsDto();
            listOfProductsDto.setProductId(product.getId());
            listOfProductsDto.setQuantity(product.getQuantity());
            listOfProductsDto.setName(product.getName());
            listOfProductsDto.setPrice(product.getPrice());
            listOfProductsDto.setDescription(product.getDescription());
            listOfProducts.add(listOfProductsDto);
        }
        getProductList.setListOfProducts(listOfProducts);
        response.put("isSuccess",true);
        response.put("message",getProductList);
        return response;
    }

    public HashMap<String,Object> createProduct(ProductDto productDto){
        Product product = new Product();
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> response1= new HashMap<>();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setDescription(productDto.getDescription());
        productRepository.save(product);
        response1.put("message","Created product id "+product.getId()+" successfully");
        response.put("isSuccess",true);
        response.put("message",response1);
        return  response;
    }
    public HashMap<String, Object> getSingleProduct(int id){
        var product= productRepository.findById(id);
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> response1= new HashMap<>();
        if(product.isEmpty()){
            response1.put("message","incorrect product id "+id+", please enter the valid id!");
            response.put("isSuccess",false);
            response.put("message",response1);
            return  response;
        }
        response.put("isSuccess",true);
        response.put("message",product);
        return response;
    }

    public HashMap<String,Object> updateProduct(ProductDto productDto){
        HashMap<String,Object> response = new HashMap<>();
        var product = productRepository.findById(productDto.getProductId()).orElse(null);
        HashMap<String,Object> response1= new HashMap<>();
        if(product==null){
            response1.put("message","incorrect Product id "+productDto.getProductId()+", please enter the valid id!");
            response.put("isSuccess",false);
            response.put("message",response1);
            return  response;
        }else{
            response1.put("message","Updated Successfully with id! "+productDto.getProductId());
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            product.setQuantity(productDto.getQuantity());
            product.setDescription(productDto.getDescription());
            response.put("isSuccess",true);
            response.put("message",response1);
            productRepository.save(product);
            return  response;
        }
    }
    public HashMap<String,Object> updateProductQuantity(GetAvailabilityDto getAvailabilityDto){
        HashMap<String,Object> response = new HashMap<>();
        var product = productRepository.findById(getAvailabilityDto.getProductId()).orElse(null);
        HashMap<String,Object> response1= new HashMap<>();
        if(product==null){
            response1.put("message","incorrect Product id "+getAvailabilityDto.getProductId()+", please enter the valid id!");
            response.put("isSuccess",false);
            response.put("message",response1);
            return  response;
        }else{
//            response1.put("message","Updated Successfully with id! ");
            product.setQuantity(getAvailabilityDto.getQuantity());
            response.put("isSuccess",true);
            response.put("message",product);
            productRepository.save(product);
            return  response;
        }
    }
    public HashMap<String,Object> deleteProduct(ProductDeleteId productDto){
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> response1= new HashMap<>();
        var product= productRepository.findById(productDto.getProductId()).orElse(null);
        if(product==null){
            response1.put("message","incorrect product id "+productDto.getProductId()+", please enter the valid id!");
            response.put("isSuccess",false);
            response.put("message",response1);
            return  response;
        }
        response1.put("message","product deleted successfully! "+productDto.getProductId());
        productRepository.deleteById(productDto.getProductId());
        response.put("isSuccess",true);
        response.put("message",response1);
        return  response;
    }

    public Boolean productsAvailabilityCheck(GetAvailabilityDto getAvailabilityDto ) {
        Product product = productRepository.findById(getAvailabilityDto.getProductId()).orElse(null);
        if (product == null) {
            return false;
        }
        else {
            if (product.getQuantity() == 0 || product.getQuantity() < getAvailabilityDto.getQuantity()) {
                return false;
            } else {
                return  true;
            }
        }
    }

}
