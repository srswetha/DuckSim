package ducksim

class MoonBling(val duckWithMoon: Duck) : Bling(duckWithMoon) {
    override fun display(): String {
        return duckWithMoon.display() + ":)"
    }
}