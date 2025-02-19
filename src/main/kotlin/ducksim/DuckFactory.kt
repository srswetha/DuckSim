package ducksim

import Subject


object DuckFactory : Subject() {
    fun createDuck(startDuck: Duck, starCount: Int, moonCount: Int, crossCount: Int): Duck {

        var duck: Duck = startDuck
        repeat(starCount) {
            duck = StarBling(duck)
        }
        repeat(moonCount) {
            duck = MoonBling(duck)
        }
        repeat(crossCount) {
            duck = CrossBling(duck)
        }
        notifyObservers()
        return duck
    }
}