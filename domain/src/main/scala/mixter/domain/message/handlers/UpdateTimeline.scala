package mixter.domain.message.handlers

import mixter.domain.message.{TimelineMessageProjection, TimelineMessageRepository}
import mixter.domain.message.event.MessageQuacked

class UpdateTimeline(timelineRepository: TimelineMessageRepository) {

  def apply(messageQuacked: MessageQuacked): Unit = {
    timelineRepository.save(TimelineMessageProjection(messageQuacked.author, messageQuacked.author, messageQuacked.message, messageQuacked.id))
  }
}

