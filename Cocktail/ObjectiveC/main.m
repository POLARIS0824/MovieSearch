#import <Foundation/Foundation.h>
#import "Cocktail.h"

int main(int argc, const char *argv[]) {
    @autoreleasepool {
        Cocktail *cocktail0 = [[Cocktail alloc] initWithName:@"Cocktail0" baseStrength:10.0f baseVolume:50 nonAlcoholicBeveragesVolume:120];
        Cocktail *cocktail1 = [[Cocktail alloc] initWithName:@"Cocktail1" baseStrength:30.0f baseVolume:100 nonAlcoholicBeveragesVolume:300];
        Cocktail *cocktail2 = [[Cocktail alloc] initWithName:@"Cocktail2" baseStrength:12.8f baseVolume:50 nonAlcoholicBeveragesVolume:150];
        Cocktail *cocktail3 = [[Cocktail alloc] initWithName:@"Soda" baseStrength:0.0f baseVolume:0 nonAlcoholicBeveragesVolume:330];
        Cocktail *cocktail4 = [[Cocktail alloc] initWithName:@"Cocktail4" baseStrength:43.2f baseVolume:80 nonAlcoholicBeveragesVolume:120];

        NSArray<Cocktail *> *cocktails = @[cocktail0, cocktail1, cocktail2, cocktail3, cocktail4];
        [cocktails sortedArrayUsingSelector:@selector(compare:)];

        for (Cocktail *cocktail in cocktails) {
            NSLog(@"%@", cocktail.description);
        }
    }
    return 0;
}
