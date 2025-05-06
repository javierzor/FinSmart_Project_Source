package com.finsmart.app.data.model

data class User(
    val id: String,
    val name: String,
    val email: String,
    val profileImageUrl: String? = null,
    val phoneNumber: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)

data class Transaction(
    val id: String,
    val amount: Double,
    val description: String,
    val category: String,
    val date: Long = System.currentTimeMillis(),
    val type: TransactionType,
    val isRecurring: Boolean = false,
    val recurringPeriod: RecurringPeriod? = null
)

enum class TransactionType {
    INCOME, EXPENSE, TRANSFER, INVESTMENT
}

enum class RecurringPeriod {
    DAILY, WEEKLY, MONTHLY, YEARLY
}

data class Budget(
    val id: String,
    val category: String,
    val amount: Double,
    val period: BudgetPeriod,
    val startDate: Long,
    val endDate: Long? = null,
    val spent: Double = 0.0
)

enum class BudgetPeriod {
    WEEKLY, MONTHLY
}

data class Investment(
    val id: String,
    val name: String,
    val amount: Double,
    val currentValue: Double,
    val type: InvestmentType,
    val riskLevel: RiskLevel,
    val startDate: Long,
    val expectedReturn: Double,
    val actualReturn: Double = 0.0
)

enum class InvestmentType {
    STOCK, BOND, ETF, MUTUAL_FUND, CRYPTO, REAL_ESTATE, OTHER
}

enum class RiskLevel {
    LOW, MEDIUM, HIGH
}

data class FinancialGoal(
    val id: String,
    val name: String,
    val targetAmount: Double,
    val currentAmount: Double = 0.0,
    val targetDate: Long,
    val category: GoalCategory,
    val isCompleted: Boolean = false
)

enum class GoalCategory {
    SAVINGS, DEBT_PAYMENT, PURCHASE, EDUCATION, RETIREMENT, TRAVEL, OTHER
}

data class EducationContent(
    val id: String,
    val title: String,
    val description: String,
    val type: ContentType,
    val difficulty: ContentDifficulty,
    val imageUrl: String? = null,
    val contentUrl: String? = null,
    val duration: Int? = null, // En minutos
    val tags: List<String> = emptyList()
)

enum class ContentType {
    ARTICLE, COURSE, VIDEO, QUIZ
}

enum class ContentDifficulty {
    BEGINNER, INTERMEDIATE, ADVANCED
}

data class Achievement(
    val id: String,
    val title: String,
    val description: String,
    val iconUrl: String,
    val isUnlocked: Boolean = false,
    val unlockedDate: Long? = null,
    val category: AchievementCategory
)

enum class AchievementCategory {
    BUDGET, SAVINGS, INVESTMENT, EDUCATION, GENERAL
}

data class FinancialTip(
    val id: String,
    val content: String,
    val category: String,
    val difficulty: ContentDifficulty
)

data class InvestmentRecommendation(
    val id: String,
    val name: String,
    val description: String,
    val type: InvestmentType,
    val riskLevel: RiskLevel,
    val expectedReturn: Double,
    val minimumAmount: Double,
    val imageUrl: String? = null
)

data class FinancialHealth(
    val score: Int, // 0-100
    val savingsRatio: Double, // Porcentaje de ingresos ahorrados
    val debtToIncomeRatio: Double, // Ratio de deuda respecto a ingresos
    val expenseToIncomeRatio: Double, // Ratio de gastos respecto a ingresos
    val investmentDiversification: Int, // 0-100
    val emergencyFundStatus: EmergencyFundStatus
)

enum class EmergencyFundStatus {
    NONE, INSUFFICIENT, ADEQUATE, EXCELLENT
}

// Modelo para la funcionalidad de Realidad Financiera Aumentada
data class ARFinanceData(
    val id: String,
    val type: ARFinanceType,
    val title: String,
    val description: String,
    val visualizationData: Map<String, Any>,
    val createdAt: Long = System.currentTimeMillis()
)

enum class ARFinanceType {
    PRODUCT_SCAN, GOAL_VISUALIZATION, EXPENSE_IMPACT, INVESTMENT_GROWTH
}

// Modelo para el asistente de IA
data class AIAssistantMessage(
    val id: String,
    val content: String,
    val isUserMessage: Boolean,
    val timestamp: Long = System.currentTimeMillis(),
    val attachments: List<AIMessageAttachment> = emptyList()
)

data class AIMessageAttachment(
    val id: String,
    val type: AIAttachmentType,
    val url: String,
    val title: String? = null
)

enum class AIAttachmentType {
    IMAGE, CHART, DOCUMENT, LINK
}
