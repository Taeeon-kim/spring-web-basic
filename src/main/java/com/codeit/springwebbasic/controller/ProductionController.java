package com.codeit.springwebbasic.controller;

import com.codeit.springwebbasic.entity.Product;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


//@Controller // -> 타임리프 같은 뷰를 직접 핸들링하는 컨트롤러, 당연히 @ResponseBody, @RepuestBody를 넣으면 타임리프같은 뷰가아닌 rest방식으로 json형태 직렬화/역질렬화 송수신하긴함
@RestController
@RequestMapping("/products")
public class ProductionController {

    // DB가 없으니 가상의 메모리 상품 저장소 선언
    private Map<Long, Product> productMap = new HashMap<>();

    // 상품의 시리얼넘버를 순차 생성
    private long nextId = 1;

    public ProductionController() {
        productMap.put(nextId, new Product(nextId, "에어컨", 1_000_000));
        nextId++;
        productMap.put(nextId, new Product(nextId, "세탁기", 1_500_000));
        nextId++;
        productMap.put(nextId, new Product(nextId, "공기청정기", 300_000));
        nextId++;
    }

    // 1. 쿼리스트링 읽기 (query string)
    // 데이터가 URl에 노출되어도 크게 문제없는 방식(조회 -> 검색어, 게시물 조회에서 글 번호 등..)
    // ?id=???&price=???

//  //  @RequestMapping(value = "/product", method = RequestMethod.GET)
//    @GetMapping("/product")
//    public Product getProduct(HttpServletRequest request) {
//        String id = request.getParameter("id");
//        System.out.println("id = " + id);
//        String price = request.getParameter("price");
//        System.out.println("price = " + price);
//        return productMap.get(Long.parseLong(id));
//    }

    // localhost:8080/products?id=???&price=???
    @GetMapping("/")
    public Product getProduct(
            @RequestParam("id") long id,
            @RequestParam(value = "price", required = false, defaultValue ="5000") int price) {

        System.out.println("id = " + id);

        System.out.println("price = " + price);
        return productMap.get(id);
    }

    // localhost:8080/products/1-> 1번 상품 조회
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productMap.get(id);
    }

}
