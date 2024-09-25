#ifndef COCKTAIL_H
#define COCKTAIL_H

typedef struct {
    char name[50];
    float baseStrength;
    int baseVolume;              
    int nonAlcoholicBeveragesVolume;
} Cocktail;

void printCocktail(Cocktail cocktail);

int compareCocktails(const void *a, const void *b);

#endif
