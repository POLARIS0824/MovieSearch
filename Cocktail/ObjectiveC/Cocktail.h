#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface Cocktail : NSObject

- (instancetype)   initWithName:(NSString *)name
                   baseStrength:(float)baseStrength
                     baseVolume:(int)baseVolume
    nonAlcoholicBeveragesVolume:(int)nonAlcoholicBeveragesVolume;

@end

NS_ASSUME_NONNULL_END
