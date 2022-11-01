import scala.math.BigDecimal.RoundingMode.HALF_UP

case class Department(var workers: List[Worker]) {

  def averageSalary(): BigDecimal = (workers.map(e => e.monthlySalary()).sum / workers.size).setScale(5, HALF_UP)

}
