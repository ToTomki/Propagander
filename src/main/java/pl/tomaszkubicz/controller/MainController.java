package pl.tomaszkubicz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.tomaszkubicz.model.ArticlesMySQL;
import pl.tomaszkubicz.ProductRepository;

@Controller
public class MainController {

    @Autowired
    public ProductRepository productRepository;

    //@RequestMapping(value = "/product/addProduct")
    @RequestMapping(value="product/addProduct")
    @ResponseBody //usunąć po
    public String addProduct(){
        StringBuilder feedback = new StringBuilder();

        ArticlesMySQL task = new ArticlesMySQL()
            .withproductName("Twarog")
            .withproductPortionWeight(100.0)
            .withproductProteins(10.0)
            .withproductCarbo(0.1)
            .withproductFats(3.0);

            productRepository.save(task);
        for(ArticlesMySQL i: productRepository.findAll()){
            feedback.append(i).append("<br>");
        }
        return feedback.toString();
    }

    @RequestMapping("/")
    public String mainList(){
        return "index";
    }




    @RequestMapping(value = "/product/{productData}", method = RequestMethod.GET)
        public String getProductData(@PathVariable("productData") String productData, Model model){
            ArticlesMySQL product = productRepository.findByProductName(productData);
        return "product";
        }

}
