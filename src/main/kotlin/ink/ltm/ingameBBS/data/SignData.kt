package ink.ltm.ingameBBS.data

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.date

object SignData : Table() {
    val uniqueID: Column<String> = text("uniqueID").uniqueIndex()
    val creatorName: Column<String> = varchar("creatorName", 20) //创建告示牌的玩家名
    val createTime: Column<LocalDate> = date("createTime") //创建时间，为2022/11/07 11:29这种
    val position: Column<String> = text("position") //放置位置
    val likeCount: Column<Int> = integer("lickCount") //点赞数
    val dislikeCount: Column<Int> = integer("dislikeCount") //点踩数
    val interactList: Column<String> = text("interactList")
    val signContent: Column<String> = text("signContent") //详情内容
    val isDeleted: Column<Boolean> = bool("isDeleted") //是否已被拆除
}

@Serializable
data class SignDataInternal(
    val uid: String,
    val creatorName: String,
    val createTime: LocalDate,
    val position: String,
    var likeCount: Int,
    var dislikeCount: Int,
    var interactList: MutableList<InteractType>,
    val signContent: String,
    val isDeleted: Boolean
)

@Serializable
data class InteractType(
    val player: String,
    var type: Boolean
)

enum class LikesResult {
    NO_SUCH_DATA,
    CANCEL_SAME,
    CANCEL_DIFFERENT,
    SUCCESS
}