package ducksim

class Flock(val duckList: List<Duck>) : Duck() {


    override var quackText: String = ""
        get() = "Noise!"

    override fun display(): String {
        return "Flock" + duckList.map { it.display().first() }.joinToString(":", ":")
    }

    override val capture = object : DuckMenuItem {
        override fun invoke() {
            duckList.forEach {
                it.doCapture()
            }
            doCapture()
        }
    }

    override val release = object : DuckMenuItem {
        override fun invoke() {
            duckList.forEach {
                it.doRelease()
            }
            doRelease()

        }
    }

    override val joinDSCW = object : DuckMenuItem {
        override fun invoke() {
            duckList.forEach {
                it.joinWC()
            }
            joinWC()
        }
    }

    override val quitDSCW = object : DuckMenuItem {
        override fun invoke() {
            duckList.forEach {
                it.quitWC()
            }
            quitWC()
        }
    }
}