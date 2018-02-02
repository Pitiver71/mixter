﻿module Mixter.Domain.Core.Timeline

open Mixter.Domain.Documentation
open Mixter.Domain.Identity.UserIdentity
open Mixter.Domain.Core.Message

[<Projection>]
type TimelineMessage = { Owner: UserId; Author: UserId; Content: string; MessageId: MessageId }

[<Handler>]
let handle (save: TimelineMessage -> unit) (remove: MessageId -> unit) (evt: Event) =
    match evt with
    | MessageQuacked e -> save { Owner = e.AuthorId; Author = e.AuthorId; Content = e.Content; MessageId = e.MessageId }
    | MessageDeleted e -> remove e.MessageId
    | _ -> ()