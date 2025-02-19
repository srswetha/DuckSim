package ducksim

class CrossBling(val duckWithCrossBling: Duck) : Bling(duckWithCrossBling) {


    override fun display(): String {
        return duckWithCrossBling.display() + ":+"
    }
}