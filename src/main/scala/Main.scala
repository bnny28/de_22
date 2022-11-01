object Main extends App {

  val aPhrase = "Hello, Scala!"

  // выводит фразу «Hello, Scala!» справа налево
  println("a-i----")
  println(aPhrase.reverse)
  println("a-ii---")

  // переводит всю фразу в нижний регистр
  println(aPhrase.toLowerCase)
  println("a-iii--")

  // удаляет символ!
  println(aPhrase.replace(",", ""))
  println("a-iv---")

  // добавляет в конец фразы «and goodbye python!»
  val aPhraseWithoutExclamation = aPhrase.replace("!", "")
  println(s"$aPhraseWithoutExclamation and goodbye python!")
  println("b-----")

  /*
  Напишите программу, которая вычисляет ежемесячный оклад сотрудника после вычета налогов.
  На вход вашей программе подается значение годового дохода до вычета налогов,
  размер премии – в процентах от годового дохода и компенсация питания.
   */
  val aWorker1 = Worker("Ivanov Ivan Ivanovich", 100, 0.25, 10)
  println(aWorker1)
  println(s"Monthly Salary: ${aWorker1.monthlySalary()}")
  println("c-----")

  /*
  Напишите программу, которая рассчитывает для каждого сотрудника отклонение(в процентах)
  от среднего значения оклада на уровень всего отдела. В итоговом значении должно учитываться в большую
  или меньшую сторону отклоняется размер оклада. На вход вышей программе подаются все значения, аналогичные
  предыдущей программе, а также список со значениями окладов сотрудников отдела 100, 150, 200, 80, 120, 75.
  */
  val aWorker2 = Worker("Sidorov Sidor Sidorovich", 150, aWorker1.bonus, aWorker1.meal)
  val aWorker3 = Worker("Petrov Petr Petrovich", 200, aWorker1.bonus, aWorker1.meal)
  val aWorker4 = Worker("Olegov Oleg Olegovich", 80, aWorker1.bonus, aWorker1.meal)
  val aWorker5 = Worker("Fedorov Fedor Fedorovich", 120, aWorker1.bonus, aWorker1.meal)
  val aWorker6 = Worker("Semenov Semen Semenovich", 75, aWorker1.bonus, aWorker1.meal)
  val department = Department(List(aWorker1, aWorker2, aWorker3, aWorker4, aWorker5, aWorker6))
  println(s"Average Salary: ${department.averageSalary()}")
  println("Percentage Deviation: ")
  department.workers.map(e => (
    e.name,
    s" AS: ${e.monthlySalary()}",
    s" PD: ${e.percentageSalaryDeviation(department.averageSalary())}%")
  ).foreach(println)
  println("d-----")

  /*
   Попробуйте рассчитать новую зарплату сотрудника, добавив(или отняв, если сотрудник плохо себя вел)
   необходимую сумму с учетом результатов прошлого задания. Добавьте его зарплату в список и
   вычислите значение самой высокой зарплаты и самой низкой.
   */
  println(aWorker6)
  println("increased salary")
  aWorker6.salary += 20
  println(aWorker6)
  println(s"Max Salary: ${department.workers.map(e => e.salary).max}")
  println(s"Min Salary: ${department.workers.map(e => e.salary).min}")
  println("e-----")

  /*
  Также в вашу команду пришли два специалиста с окладами 350 и 90 тысяч рублей.
  Попробуйте отсортировать список сотрудников по уровню оклада от меньшего к большему.
   */
  val aWorker7 = Worker("Dimitrov Dimitr Dimitrovich", 350, aWorker1.bonus, aWorker1.meal)
  val aWorker8 = Worker("Kostev Konstantin Konstantinovich", 90, aWorker1.bonus, aWorker1.meal)
  department.workers = (department.workers :+ aWorker7 :+ aWorker8).sortWith(_.salary < _.salary)
  department.workers.foreach(println)
  println("f-----")

  /*
  Кажется, вы взяли в вашу команду еще одного сотрудника и предложили ему оклад 130 тысяч.
  Вычислите самостоятельно номер сотрудника в списке так, чтобы сортировка не нарушилась
  и добавьте его на это место.
   */
  val aWorker9 = Worker("Genov Genadi Genadievich", 130, aWorker1.bonus, aWorker1.meal)
  val position = department.workers.zipWithIndex.filter(e => e._1.salary >= aWorker9.salary).head._2

  def insert[T](list: List[T], i: Int, values: T*): List[T] = {
    val (front, back) = list.splitAt(i)
    front ++ values ++ back
  }

  department.workers = insert(department.workers, position, aWorker9)
  department.workers.foreach(println)
  println("g-----")

  /*
  Попробуйте вывести номера сотрудников из полученного списка, которые попадают под категорию middle.
  На входе программе подается «вилка» зарплаты специалистов уровня middle.
   */
  val rangeMiddle = (100, 150)
  department.workers.filter(e => (rangeMiddle._1 <= e.salary) && (e.salary <= rangeMiddle._2)).foreach(println)
  println("h-----")

  /*
  Однако наступил кризис и ваши сотрудники требуют повысить зарплату.
  Вам необходимо проиндексировать зарплату каждого сотрудника на уровень инфляции – 7%
   */
  val percent = 7
  department.workers = department.workers.map(e => {
    e.salary += e.salary * percent / 100
    e
  })
  department.workers.foreach(println)
  println("------")
}