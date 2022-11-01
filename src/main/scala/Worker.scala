import scala.math.BigDecimal.RoundingMode.HALF_UP

case class Worker(name: String,
                  var salary: BigDecimal,
                  var bonus: Double,
                  var meal: BigDecimal) {

  def monthlySalary(): BigDecimal = (salary * (1 + bonus) + meal) * 0.87 / 12

  def percentageSalaryDeviation(monthlyWhole: BigDecimal): BigDecimal = (((monthlySalary() / monthlyWhole) - 1) * 100)
    .setScale(2, HALF_UP)
}