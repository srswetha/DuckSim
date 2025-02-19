package ducksim

import java.awt.Color

class GooseDuck(private val goose: Goose) : Duck(FlyWithWings(), QuackHonk()) {
    override val color: Color = Color.BLUE
    override fun display() = goose.name

}