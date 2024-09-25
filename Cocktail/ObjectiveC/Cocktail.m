#import "Cocktail.h"

@interface Cocktail ()
@property (nonatomic, nonnull, strong) NSString *name;
@property (nonatomic, assign) float baseStrength;
@property (nonatomic, assign) int baseVolume;
@property (nonatomic, assign) int nonAlcoholicBeveragesVolume;

@end

@implementation Cocktail

- (instancetype)initWithName:(NSString *)name baseStrength:(float)baseStrength baseVolume:(int)baseVolume nonAlcoholicBeveragesVolume:(int)nonAlcoholicBeveragesVolume {
    if (self = [super init]) {
        _name = name;
        _baseStrength = baseStrength;
        _baseVolume = baseVolume;
        _nonAlcoholicBeveragesVolume = nonAlcoholicBeveragesVolume;
    }

    return self;
}

@end
