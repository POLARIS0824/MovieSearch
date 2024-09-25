import Foundation


let cocktail0 = Cocktail(name: "Cocktail0", baseStrength: 10.0, baseVolume: 50, nonAlcoholicBeveragesVolume: 120)
let cocktail1 = Cocktail(name: "Cocktail1", baseStrength: 30.0, baseVolume: 100, nonAlcoholicBeveragesVolume: 300)
let cocktail2 = Cocktail(name: "Cocktail2", baseStrength: 12.8, baseVolume: 50, nonAlcoholicBeveragesVolume: 150)
let cocktail3 = Cocktail(name: "Soda", baseStrength: 0.0, baseVolume: 0, nonAlcoholicBeveragesVolume: 330)
let cocktail4 = Cocktail(name: "Cocktail4", baseStrength: 43.2, baseVolume: 80, nonAlcoholicBeveragesVolume: 120)

let cocktailList = [cocktail0, cocktail1, cocktail2, cocktail3, cocktail4]

cocktailList.sorted(using: <)

cocktailList.forEach { cocktail in
    print(cocktail)
}
