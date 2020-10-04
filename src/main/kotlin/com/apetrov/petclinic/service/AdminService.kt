package com.apetrov.petclinic.service

import com.apetrov.petclinic.dao.BranchDao
import com.apetrov.petclinic.dao.ClientDao
import com.apetrov.petclinic.dao.DoctorDao
import com.apetrov.petclinic.dao.ReceptionDao
import com.apetrov.petclinic.enums.Position
import com.apetrov.petclinic.model.Branch
import com.apetrov.petclinic.model.Client
import com.apetrov.petclinic.model.Doctor
import com.apetrov.petclinic.model.Reception
import com.apetrov.petclinic.rest.indto.DoctorInDto
import org.joda.time.*
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*
import javax.transaction.Transactional


@Service
class AdminService(val doctorDao: DoctorDao, val clientDao: ClientDao, val receptionDao: ReceptionDao,
                   val branchDao: BranchDao, val passwordEncoder: BCryptPasswordEncoder) {
    private lateinit var startTime: LocalDateTime

    @Transactional
    fun addDoctor(dto: DoctorInDto) {
        val doctor = Doctor(
                dto.specialization,
                dto.position,
                dto.name,
                dto.surname,
                dto.birthday,
                dto.salary,
                dto.careerStartYear,
                dto.photoUrl,
                branchDao.findById(dto.branchId ?: (1L)).get()
        )
        doctorDao.save(doctor)
    }

    @Transactional
    fun initDoctorsAndUnits() {
        val therapyBranch=Branch("Терапия")
        val surgeonBranch=Branch("Хирургия")
        val reanimationBranch=Branch("Реанимация")
        val doctor = Doctor(
                "Зоопсихолог",
                Position.LEAD_SPECIALIST,
                "Анастасия",
                "Павлова",
                LocalDate(1985, 10, 5),
                BigDecimal(80000),
                2010,
                "https://intalent.pro/sites/default/files/styles/new_photo_in_article/public/foto/article/1_14.jpg",
                therapyBranch
        )
        val doctor2 = Doctor(
                "Ветеринар-кинолог",
                Position.SPECIALIST,
                "Антон",
                "Вольнов",
                LocalDate(1987, 5, 1),
                BigDecimal(60000),
                2015,
                "https://www.asi.org.ru/wp-content/uploads/2019/10/6q_mEYpRZb4.jpg",
                therapyBranch
        )
        val doctor3 = Doctor(
                "Ветеринар-кардиолог",
                Position.SPECIALIST,
                "Игорь",
                "Борисов",
                LocalDate(1991, 1, 1),
                BigDecimal(90000),
                2011,
                "https://276709.selcdn.ru/proektoria/new/professions/2020/03/24/3644a684f98ea8fe223c713b77189a77/2019-12-08_13-14-23.jpg",
                surgeonBranch
        )
        val doctor4 = Doctor(
                "Ветеринар-ипполог",
                Position.SENIOR_SPECIALIST,
                "Дарья",
                "Прокопенко",
                LocalDate(1981, 5, 1),
                BigDecimal(140000),
                2005,
                "http://1uilim.e-stile.ru/images/loshad4.jpg",
                therapyBranch
        )
        val doctor5 = Doctor(
                "Ветеринар-фелинолог",
                Position.HEAD_OF_UNIT,
                "Юрий",
                "Куклачев",
                LocalDate(1967, 1, 1),
                BigDecimal(190000),
                1985,
                "https://cdn21.img.ria.ru/images/155238/17/1552381746_105:0:2836:2048_1400x0_80_0_0_30c2fa165bcd37f0ea7164b249c2f21d.jpg",
                therapyBranch
        )
        val doctor6= Doctor(
                "Ветеринар-орнитолог",
                Position.SENIOR_SPECIALIST,
                "Николай",
                "Дроздов",
                LocalDate(1950, 10, 5),
                BigDecimal(200000),
                1978,
                "https://www.pervo.ru/uploads/posts/2017-09/1504610691_pervoru-2118.jpg",
                surgeonBranch
        )
        val doctor7=Doctor(
                "Ветеринар-реаниматолог",
                Position.HEAD_OF_UNIT,
                "Аркадий",
                "Кукушкин",
                LocalDate(1983, 10, 5),
                BigDecimal(190000),
                2004,
                "https://www.biocontrol.ru/wp-content/uploads/2008/05/Захаров-Егор-Владимирович.jpg",
                reanimationBranch
        )
        val doctor8=Doctor(
                "Ветеринар-реаниматолог",
                Position.SENIOR_SPECIALIST,
                "Елена",
                "Никулина",
                LocalDate(1990, 10, 5),
                BigDecimal(100000),
                2014,
                "https://shop-cdn1.vigbo.tech/shops/6858/products/215784/images/3-df1f61f2d6629401198e0a6020d9096c.jpg",
                reanimationBranch
        )
        doctorDao.saveAll(arrayListOf(doctor, doctor2, doctor3, doctor4, doctor5, doctor6, doctor7, doctor8))
    }

    @Transactional
    fun initClients() {
        val client = Client(
                "Максим",
                "Петухов",
                "9007005031",
                passwordEncoder.encode("1")
        )
        val client2 = Client(
                "Антон",
                "Феофанов",
                "9007005032",
                passwordEncoder.encode("1")
        )
        val client3 = Client(
                "Эльвира",
                "Мкртчан",
                "9007005033",
                passwordEncoder.encode("1")
        )
        clientDao.saveAll(arrayListOf(client, client2, client3))
    }

    @Transactional
    fun initSchedule() {
        val doctors = doctorDao.findAll()
        val clients = clientDao.findAll()
        createReceptions(doctors, clients)
    }
    fun initStartTime(){
        startTime= LocalDateTime.now()
    }
    fun getUptime():String{
        val minutesUp= Minutes.minutesBetween(startTime, LocalDateTime.now()).minutes
        val days=minutesUp/24/60
        val hours=(minutesUp-(days*24*60))/60
        val minutes=minutesUp%60
        return String.format("Сервер в сети уже %s дней, %s часов, %s минут,\nЗапущен: %s \n%s", days, hours, minutes, startTime, getMemoryUsage())
    }
    fun getMemoryUsage():String{
        val runtime = Runtime.getRuntime()

        val format: NumberFormat = NumberFormat.getInstance()

        val sb = StringBuilder()
        val maxMemory = runtime.maxMemory()
        val allocatedMemory = runtime.totalMemory()
        val freeMemory = runtime.freeMemory()

        sb.append("Свободная память: " + format.format(freeMemory / 1024).toString() + "\n")
        sb.append("Выделенная память: " + format.format(allocatedMemory / 1024).toString() + "\n")
        sb.append("Максимально доступная память JVM: " + format.format(maxMemory / 1024).toString() + "\n")
        sb.append("Всего доступной (свободной) памяти: " + format.format((freeMemory + (maxMemory - allocatedMemory)) / 1024).toString() + "\n")
        return sb.toString()
    }

    private fun createReceptions(doctors: MutableList<Doctor>, clients: MutableList<Client>){
        val now = LocalDate()
        var currentDay: LocalDate = now.withDayOfWeek(DateTimeConstants.MONDAY)
        val endOfThePeriod = currentDay.plusDays(28)
        while (currentDay.isBefore(endOfThePeriod)) {
            var curentTime = currentDay.toLocalDateTime(LocalTime(9, 0))
            while (curentTime.isBefore(currentDay.toLocalDateTime(LocalTime(20, 50)))) {
                val endtime = curentTime.plusMinutes(30)
                doctors.forEach {
                    val occupied = Random().nextInt(10) > 4
                    if (occupied) {
                        it.receptions.add(
                                Reception(curentTime, endtime, it,
                                        clients.stream().skip(Random().nextInt(clients.size - 1).toLong()).findFirst().get()))
                    }
                    else {
                        it.receptions.add(
                                Reception(curentTime, endtime, it, null))
                    }

                }
                curentTime = endtime
            }
            currentDay = currentDay.plusDays(1)
        }
        doctorDao.saveAll(doctors)
    }

}