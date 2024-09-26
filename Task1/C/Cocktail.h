#ifndef COCKTAIL_H
#define COCKTAIL_H

typedef struct {
    char name[50];
    float baseStrength; //基础酒精含量
    int baseVolume;     //基础酒精体积         
    int nonAlcoholicBeveragesVolume;  //非酒精饮料体积
} Cocktail;

void printCocktail(Cocktail cocktail);

int compareCocktails(const void *a, const void *b);

#endif
