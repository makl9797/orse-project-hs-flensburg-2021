package models

import com.benasher44.uuid.Uuid
import dev.fritz2.lenses.Lenses

@Lenses
data class Employee(val _id: Uuid, val employeeRole: Role)
