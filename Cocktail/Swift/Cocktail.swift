import Foundation

struct Cocktail {
    private let name: String
    private let baseStrength: Float
    private let baseVolume: Int
    private let nonAlcoholicBeveragesVolume: Int
    
    init(name: String, baseStrength: Float, baseVolume: Int, nonAlcoholicBeveragesVolume: Int) {
        self.name = name
        self.baseStrength = baseStrength
        self.baseVolume = baseVolume
        self.nonAlcoholicBeveragesVolume = nonAlcoholicBeveragesVolume
    }
}
