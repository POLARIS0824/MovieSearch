#include <stddef.h>
#include <stdio.h>
#include <stdlib.h>
#include "Cocktail.h"

int main() {
    Cocktail cocktailList[] = {
        {"Cocktail0", 10.0f, 50, 120},
        {"Cocktail1", 30.0f, 100, 300},
        {"Cocktail2", 12.8f, 50, 150},
        {"Soda", 0.0f, 0, 330},
        {"Cocktail4", 43.2f, 80, 120}
    };

    size_t numCocktails = sizeof(cocktailList) / sizeof(cocktailList[0]);

    qsort(cocktailList, numCocktails, sizeof(Cocktail), compareCocktails);

    for (size_t i = 0; i < numCocktails; ++i) {
        printCocktail(cocktailList[i]);
    }

    return EXIT_SUCCESS;
}