package com.graduationproject.graduationproject.controller;

import com.graduationproject.graduationproject.entity.*;
import com.graduationproject.graduationproject.entity.body.PageBody1;
import com.graduationproject.graduationproject.entity.body.UserBody1;
import com.graduationproject.graduationproject.repository.ProductRepository;
import com.graduationproject.graduationproject.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/farmStaff")
// 农场员工功能
public class FarmStaffController {

    @Autowired
    private ProductService productService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ConsumptionService consumptionService;

    @Autowired
    private ProduceService produceService;

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/initProduct")
    private Map initProduct(@RequestBody String productType) {
        //System.out.println("post success!" + productType);

        int page = 1;
        int pages = 0;
        List<Integer> pageList = new ArrayList<Integer>();

        List<Product> productList = new ArrayList<Product>();
        productList = productService.findByProductType(productType);

        if (productList.size() / 5 >= 5) {
            for (int i = 0; i < 5; i++) {
                pageList.add(i + 1);
            }

            if (productList.size() % 5 != 0) {
                pages = (productList.size() + 5) / 5;
            } else {
                pages = productList.size() / 5;
            }
            productList = productList.subList(0, 5);
        } else {
            if (!productList.isEmpty()) {
                if (productList.size() % 5 != 0) {
                    pages = (productList.size() + 5) / 5;
                } else {
                    pages = productList.size() / 5;
                }

                for (int i = 0; i < pages; i++) {
                    pageList.add(i + 1);
                }

                if (productList.size() < 5) {
                    productList = productList.subList(0, productList.size());
                } else {
                    productList = productList.subList(0, 5);
                }
            }
        }

        PageBody1 pageBody1 = new PageBody1();
        pageBody1.setPage(page);
        pageBody1.setPages(pages);
        pageBody1.setPageList(pageList);

        return Map.of("productList", productList, "pageBody1", pageBody1);
    }

    @PostMapping("/doPage/{productType}")
    // 农产品分页
    public Map doPage(@RequestBody PageBody1 pageBody1,
                      @PathVariable String productType) {
        //System.out.println("post success!" + pageBody1.getPage() + productType);

        List<Integer> pageList = new ArrayList<Integer>();
        List<Product> productList = new ArrayList<Product>();

        productList = productService.findByProductType(productType);
        if (productList.isEmpty()) {
            pageBody1.setPage(0);
            pageBody1.setPages(0);
            pageBody1.setPageList(pageList);
        } else {
            if ((double) productList.size() / 5 > 5.0) {
                if (productList.size() % 5 != 0) {
                    pageBody1.setPages((productList.size() + 5) / 5);
                } else {
                    pageBody1.setPages(productList.size() / 5);
                }

                if (pageBody1.getPage() <= pageBody1.getPages()) {
                    if (pageBody1.getPage() + 2 > pageBody1.getPages()) {
                        for (int i = pageBody1.getPages() - 5; i < pageBody1.getPages(); i++) {
                            pageList.add(i + 1);
                        }
                    } else {
                        pageList.add(pageBody1.getPage() - 2);
                        pageList.add(pageBody1.getPage() - 1);
                        pageList.add(pageBody1.getPage());
                        pageList.add(pageBody1.getPage() + 1);
                        pageList.add(pageBody1.getPage() + 2);
                    }
                } else {
                    pageBody1.setPage(pageBody1.getPages());
                    for (int i = pageBody1.getPages() - 5; i < pageBody1.getPages(); i++) {
                        pageList.add(i + 1);
                    }
                }
            } else {
                if (productList.size() % 5 != 0) {
                    pageBody1.setPages((productList.size() + 5) / 5);
                } else {
                    pageBody1.setPages(productList.size() / 5);
                }

                if (pageBody1.getPage() <= pageBody1.getPages()) {
                    for (int i = 0; i < pageBody1.getPages(); i++) {
                        pageList.add(i + 1);
                    }
                } else {
                    pageBody1.setPage(pageBody1.getPages());
                    for (int i = 0; i < pageBody1.getPages(); i++) {
                        pageList.add(i + 1);
                    }
                }
            }

            productList = productList.subList(pageBody1.getPage() * 5 - 5, pageBody1.getPage() * 5 > productList.size() ? productList.size() : pageBody1.getPage() * 5);
            pageBody1.setPageList(pageList);
        }

        return Map.of("productList", productList, "pageBody1", pageBody1);
    }

    @PostMapping("changePassword")
    // 修改密码
    public Map changePassword(@RequestBody UserBody1 userBody1) {
        //System.out.println("post success!" + userBody1.getUsername());

        String message = "";
        Staff staff = new Staff();

        staff = staffService.findByUsername(userBody1.getUsername());
        if (!passwordEncoder.matches(userBody1.getPassword(), staff.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "旧密码错误！");
        } else {
            staff.setPassword(passwordEncoder.encode(userBody1.getNewPassword()));
            staffService.updateStaff(staff);
            message = "修改成功！";
        }

        return Map.of("message", message);
    }

    @PostMapping("addProduct")
    // 添加农场品
    public Map addProduct(@RequestBody Product product) {
        //System.out.println("post success!" + product.getProductType());

        String message = "";

        if (productService.findByName(product.getName()) != null) {
            message = "该农产品已存在！";
        } else {
            product.setBaseAmount(0);
            productService.save(product);
            message = "添加成功！";
        }

        return Map.of("message", message);
    }

    @PostMapping("deleteProduct")
    // 删除农产品
    public Map deleteProduct(@RequestBody Product product) {
        //System.out.println("post success!" + product.getProductType() + product.getId());

        Product product1 = productService.findByName(product.getName());
        productService.deleteProduct(product1);

        return Map.of("message", "删除成功！");
    }

    @PostMapping("modifyProduct")
    // 修改农产品
    public Map modifyProduct(@RequestBody Product product) {
        //System.out.println("post success!" + product.getName());

        productService.save(product);

        return Map.of("message", "修改成功！");
    }

    @PostMapping("abnormalConsumption")
    // 异常消耗
    public Map abnormalConsumption(@RequestBody Product product) {
        //System.out.println("post success!"+product.getAmount());

        String message = "提交成功！";
        Product product1 = productService.findByName(product.getName());
        if (product.getProductType().equals(Product.productType1)) {
            product1.setAmount(product1.getAmount() - product.getAmount());
        } else if (product.getProductType().equals(Product.productType2)) {
            product1.setAmount(product1.getAmount() - product.getAmount());
        } else if (product.getProductType().equals(Product.productType3)) {
            product1.setAmount(product1.getAmount() - product.getAmount());
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "产品类型错误！");
        }
        productService.save(product1);

        return Map.of("message", message);
    }

    @PostMapping("produce")
    // 生产农产品
    public Map produce(@RequestBody Product product) {
        //System.out.println("post success!" + product.getAmount());

        String message = "提交成功！";
        Product product1 = productService.findByName(product.getName());
        if (product.getProductType().equals(Product.productType1)) {
            product1.setBaseAmount(product1.getBaseAmount() + product.getAmount());
        } else if (product.getProductType().equals(Product.productType2)) {
            product1.setAmount(product.getAmount() + product1.getAmount());
        } else if (product.getProductType().equals(Product.productType3)) {
            product1.setAmount(product.getAmount() + product1.getAmount());
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "产品类型错误！");
        }
        productService.save(product1);

        return Map.of("message", message);
    }

    @PostMapping("abnormalConsumption1")
    // 异常消耗（原料）
    public Map abnormalConsumption1(@RequestBody Product product) {
        //System.out.println("post success!"+product.getAmount());

        String message = "提交成功！";
        Product product1 = productService.findByName(product.getName());
        if (product.getProductType().equals(Product.productType1)) {
            product1.setBaseAmount(product1.getBaseAmount() - product.getAmount());
        } else if (product.getProductType().equals(Product.productType2)) {
            product1.setAmount(product1.getAmount() - product.getAmount());
        } else if (product.getProductType().equals(Product.productType3)) {
            product1.setBaseAmount(product1.getBaseAmount() - product.getAmount());
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "产品类型错误！");
        }
        productService.save(product1);

        return Map.of("message", message);
    }

    @PostMapping("addPurchase")
    // 采购原料
    public Map addPurchase(@RequestBody Purchase purchase) {
        //System.out.println("post success!" + purchase.getAmount() + purchase.getPrice() + purchase.getProduct().getName());

        Purchase purchase1 = new Purchase();
        Product product = productService.findByName(purchase.getProduct().getName());

        if (product.getProductType().equals(Product.productType1)) {
            product.setBaseAmount(product.getBaseAmount() + purchase.getAmount());
        } else if (product.getProductType().equals(Product.productType2)) {
            product.setAmount(product.getAmount() + purchase.getAmount());
        } else if (product.getProductType().equals(Product.productType3)) {
            product.setBaseAmount(product.getBaseAmount() + purchase.getAmount());
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "产品类型错误！");
        }
        productService.save(product);

        purchase1.setAmount(purchase.getAmount());
        purchase1.setPrice(purchase.getPrice());
        purchase1.setProduct(product);
        purchaseService.save(purchase1);

        return Map.of("message","提交成功！");
    }

    @PostMapping("produce1")
    // 生产农产品
    public Map produce1(@RequestBody Product product) {
        //System.out.println("post success!" + product.getAmount());

        String message = "提交成功！";
        Product product1 = productService.findByName(product.getName());
        if (product.getProductType().equals(Product.productType1)) {
            product1.setAmount(product1.getAmount() + product.getAmount());
            product1.setBaseAmount(product1.getBaseAmount()-product.getAmount());
        } else if (product.getProductType().equals(Product.productType2)) {
            product1.setAmount(product.getAmount() + product1.getAmount());
        } else if (product.getProductType().equals(Product.productType3)) {
            product1.setAmount(product.getAmount() + product1.getAmount());
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "产品类型错误！");
        }
        productService.save(product1);

        return Map.of("message", message);
    }
}
