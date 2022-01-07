package com.carto.server;

import com.carto.server.model.CartoUser;
import com.carto.server.model.ProductCategory;
import com.carto.server.modules.product.ProductCategoryService;
import com.carto.server.modules.user.CartoUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

@SpringBootApplication
@RequiredArgsConstructor
public class CartoServerApplication {

    private final CartoUserRepository cartoUserRepository;

    @Bean
    public Function<String, CartoUser> fetchUser() {
        return (this.cartoUserRepository::findByFirebaseId);
    }

    @Bean
    public CommandLineRunner loadCategories(ProductCategoryService productCategoryService) {
        return args -> {
            Set<ProductCategory> categories = new HashSet<>();

            categories.add(new ProductCategory(
                    null,
                    "Apparel and Clothing",
                    "APPAREL",
                    "https://firebasestorage.googleapis.com/v0/b/carto-full-stack.appspot.com/o/categories%2FAPPAREL.jpg?alt=media&token=3dced88e-66d2-4118-a25c-6b5cc9bc107b",
                    null)
            );

            categories.add(new ProductCategory(
                    null,
                    "Books and Novel",
                    "BOOKS",
                    "https://firebasestorage.googleapis.com/v0/b/carto-full-stack.appspot.com/o/categories%2FBOOKS.jpg?alt=media&token=24cd2478-e5d2-4ac7-bff3-4feb7c83c433",
                    null)
            );

            categories.add(new ProductCategory(
                    null,
                    "Automotive Parts and Customization",
                    "CARS",
                    "https://firebasestorage.googleapis.com/v0/b/carto-full-stack.appspot.com/o/categories%2FCARS.jpg?alt=media&token=6eb0f972-a2ca-4497-adae-882fdb3620db",
                    null)
            );

            categories.add(new ProductCategory(
                    null,
                    "Electronics and Various Gadgets",
                    "ELECTRONICS",
                    "https://firebasestorage.googleapis.com/v0/b/carto-full-stack.appspot.com/o/categories%2FELECTRONICS.jpg?alt=media&token=98b4dbf7-620a-4679-a114-11bafd05f2f3",
                    null)
            );

            categories.add(new ProductCategory(
                    null,
                    "Games and Consoles",
                    "GAMES",
                    "https://firebasestorage.googleapis.com/v0/b/carto-full-stack.appspot.com/o/categories%2FGAMES.jpg?alt=media&token=8b353e45-5766-4f8d-ba3b-112690c55554",
                    null)
            );

            categories.add(new ProductCategory(
                    null,
                    "Jewellery and Novelties",
                    "JEWELRY",
                    "https://firebasestorage.googleapis.com/v0/b/carto-full-stack.appspot.com/o/categories%2FJEWELRY.jpg?alt=media&token=67a21760-0215-4e41-8d22-8e592527bcae",
                    null)
            );

            categories.add(new ProductCategory(
                    null,
                    "Musical Instruments and Albums",
                    "MUSIC",
                    "https://firebasestorage.googleapis.com/v0/b/carto-full-stack.appspot.com/o/categories%2FMUSIC.jpg?alt=media&token=99250e0f-b4e5-4ea2-919f-9fabd5c774f2",
                    null)
            );

            categories.add(new ProductCategory(
                    null,
                    "Pets and Grooming",
                    "PETS",
                    "https://firebasestorage.googleapis.com/v0/b/carto-full-stack.appspot.com/o/categories%2FPETS.jpg?alt=media&token=5f4a1f08-8377-4b29-b3e9-272fcb0d41db",
                    null)
            );

            categories.add(new ProductCategory(
                    null,
                    "Shoes and Boots",
                    "SHOES",
                    "https://firebasestorage.googleapis.com/v0/b/carto-full-stack.appspot.com/o/categories%2FSHOES.jpg?alt=media&token=39891be1-b313-47b3-974c-06d3f2621dcf",
                    null)
            );

            categories.add(new ProductCategory(
                    null,
                    "Sports and Fitness",
                    "SPORTS",
                    "https://firebasestorage.googleapis.com/v0/b/carto-full-stack.appspot.com/o/categories%2FSPORTS.jpg?alt=media&token=23ba4075-e9e1-415b-be72-7bfe632ed0ef",
                    null)
            );

            productCategoryService.loadCategories(categories);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(CartoServerApplication.class, args);
    }

}
