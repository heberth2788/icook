package com.yape.icook.mock

import com.yape.icook.data.entity.FoodRecipeResponse

/**
 * Create mock data for testing purposes
 */
internal fun createMockData(): List<FoodRecipeResponse> = listOf(
    mockFoodRecipeResponse,
    FoodRecipeResponse(id = 10, name = "Lomo Saltado", desc = "Delicious lomo saltado" ),
    FoodRecipeResponse(id = 11, name = "Pollo a la brasa", desc = "Delicious pollo a la brasa" ),
    FoodRecipeResponse(id = 12, name = "Papa a la huancaina", desc = "Delicious papa a la huancaina" ),
    FoodRecipeResponse(id = 13, name = "Arroz con pato", desc = "Delicious arroz con pato" ),
    FoodRecipeResponse(id = 14, name = "Tacu tacu", desc = "Delicious tacu tacu" ),
    FoodRecipeResponse(id = 15, name = "Picarones", desc = "Delicious picarones" ),
    FoodRecipeResponse(id = 16, name = "Tiradito", desc = "Delicious tiradito" ),
    FoodRecipeResponse(id = 17, name = "Cau Cau", desc = "Delicious cau cau" ),
    FoodRecipeResponse(id = 18, name = "Aji de gallina", desc = "Delicious aji de gallina" ),
    FoodRecipeResponse(id = 19, name = "Causa rellena", desc = "Delicious causa rellena" ),
    FoodRecipeResponse(id = 20, name = "Arroz con pollo", desc = "Delicious arroz con pollo" ),
    FoodRecipeResponse(id = 21, name = "Carapulcra", desc = "Delicious carapulcra" ),
    FoodRecipeResponse(id = 22, name = "Seco de carne", desc = "Delicious seco de carne" ),
    FoodRecipeResponse(id = 23, name = "Arroz chaufa", desc = "Delicious arroz chaufa" ),
    FoodRecipeResponse(id = 24, name = "Anticuchos", desc = "Delicious anticuchos" ),
    FoodRecipeResponse(id = 25, name = "Chicharron", desc = "Delicious chicarron" ),
    FoodRecipeResponse(id = 26, name = "Pachamanca", desc = "Delicious pachamanca" ),
    FoodRecipeResponse(id = 1, name = "Pizza", desc = "Delicious pizza" ),
    FoodRecipeResponse(id = 2, name = "Burger", desc = "Delicious burger" ),
    FoodRecipeResponse(id = 3, name = "Salad", desc = "Delicious salad" ),
    FoodRecipeResponse(id = 4, name = "Pasta", desc = "Delicious pasta" ),
    FoodRecipeResponse(id = 5, name = "Sushi", desc = "Delicious sushi" ),
    FoodRecipeResponse(id = 6, name = "Tacos", desc = "Delicious tacos" ),
    FoodRecipeResponse(id = 7, name = "Steak", desc = "Delicious steak" ),
    FoodRecipeResponse(id = 8, name = "Ice Cream", desc = "Delicious ice cream" ),
)

/**
 * The first mock food recipe
 */
internal val mockFoodRecipeResponse = FoodRecipeResponse(
    id = 9,
    name = "Ceviche",
    imageUrl = "https://sarahkoszyk.com/wp-content/uploads/2013/10/ceviche_final.jpg",
    desc = "Delicious peruvian ceviche",
    lat = -12.0574941,
    lng = -77.0341247,
    ingredients = "1 pound jumbo shrimp, peeled and deveined.\n" +
        "5 large lemons, juiced, or as needed.\n" +
        "2 white onions, finely chopped.\n" +
        "1 large tomato, seeded and chopped.\n" +
        "1 cucumber, peeled and finely chopped.\n" +
        "1 bunch radishes, finely diced.\n" +
        "2 cloves fresh garlic, minced.\n" +
        "3 fresh jalapeño peppers, seeded and minced.",
    preparation = "Chunks of raw fish, " +
        "marinated in freshly squeezed key lime, with sliced onions, chili peppers, salt and " +
        "pepper. Corvina or cebo (sea bass) was the fish traditionally used"
)