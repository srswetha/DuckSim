package ducksim

class StarBling(val duckWithStars: Duck) : Bling(duckWithStars) {
    override fun display(): String {
        return duckWithStars.display() + ":*"
    }
}