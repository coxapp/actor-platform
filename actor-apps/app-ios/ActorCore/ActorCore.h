//
//  Copyright (c) 2014-2015 Actor LLC. <https://actor.im>
//

#ifndef ActorApp_ActorCore_h
#define ActorApp_ActorCore_h

// J2OBJC

#import "J2ObjC_header.h"
#import "IOSClass.h"

#import "java/lang/Exception.h"
#import "java/lang/Runnable.h"
#import "java/lang/Boolean.h"
#import "java/lang/Long.h"
#import "java/lang/Integer.h"
#import "java/util/List.h"
#import "java/util/ArrayList.h"
#import "java/util/HashSet.h"
#import "java/util/Map.h"
#import "java/util/HashMap.h"
#import "java/math/BigInteger.h"

#import "im/actor/core/CocoaMessenger.h"
#import "im/actor/core/Messenger.h"
#import "im/actor/core/ActorAnalytics.h"
#import "im/actor/core/ApiConfiguration.h"
#import "im/actor/core/PlatformType.h"
#import "im/actor/core/AuthState.h"
#import "im/actor/core/Configuration.h"
#import "im/actor/core/ConfigurationBuilder.h"
#import "im/actor/core/DeviceCategory.h"
#import "im/actor/core/NotificationProvider.h"
#import "im/actor/core/PhoneBookProvider.h"

#import "im/actor/core/entity/Avatar.h"
#import "im/actor/core/entity/AvatarImage.h"
#import "im/actor/core/entity/Contact.h"
#import "im/actor/core/entity/ContentType.h"
#import "im/actor/core/entity/Dialog.h"
#import "im/actor/core/entity/SearchEntity.h"
#import "im/actor/core/entity/FileReference.h"
#import "im/actor/core/entity/Message.h"
#import "im/actor/core/entity/MessageState.h"
#import "im/actor/core/entity/Peer.h"
#import "im/actor/core/entity/PeerType.h"
#import "im/actor/core/entity/User.h"
#import "im/actor/core/entity/Sex.h"
#import "im/actor/core/entity/GroupMember.h"
#import "im/actor/core/entity/content/AbsContent.h"
#import "im/actor/core/entity/content/TextContent.h"
#import "im/actor/core/entity/content/DocumentContent.h"
#import "im/actor/core/entity/content/ServiceContent.h"
#import "im/actor/core/entity/content/PhotoContent.h"
#import "im/actor/core/entity/content/VideoContent.h"
#import "im/actor/core/entity/content/FastThumb.h"
#import "im/actor/core/entity/content/FileSource.h"
#import "im/actor/core/entity/content/FileLocalSource.h"
#import "im/actor/core/entity/content/FileRemoteSource.h"
#import "im/actor/core/entity/content/BannerContent.h"
#import "im/actor/core/entity/PhoneBookContact.h"
#import "im/actor/core/entity/PhoneBookPhone.h"
#import "im/actor/core/entity/PhoneBookEmail.h"
#import "im/actor/core/entity/ContentType.h"
#import "im/actor/core/entity/FileReference.h"
#import "im/actor/core/entity/Notification.h"
#import "im/actor/core/entity/ContentDescription.h"
#import "im/actor/core/entity/PublicGroup.h"
#import "im/actor/core/entity/MentionFilterResult.h"

#import "im/actor/core/analytics/AllEvents.h"
#import "im/actor/core/analytics/Event.h"
#import "im/actor/core/analytics/Page.h"

#import "im/actor/core/viewmodel/UserVM.h"
#import "im/actor/core/viewmodel/UserTypingVM.h"
#import "im/actor/core/viewmodel/GroupVM.h"
#import "im/actor/core/viewmodel/GroupTypingVM.h"
#import "im/actor/core/viewmodel/UserPhone.h"
#import "im/actor/core/viewmodel/UserPresence.h"
#import "im/actor/core/viewmodel/UploadFileVMCallback.h"
#import "im/actor/core/viewmodel/UploadFileVM.h"
#import "im/actor/core/viewmodel/FileCallback.h"
#import "im/actor/core/viewmodel/UploadFileCallback.h"
#import "im/actor/core/viewmodel/AppStateVM.h"
#import "im/actor/core/viewmodel/AvatarUploadState.h"
#import "im/actor/core/viewmodel/GroupAvatarVM.h"
#import "im/actor/core/viewmodel/OwnAvatarVM.h"
#import "im/actor/core/viewmodel/Command.h"
#import "im/actor/core/viewmodel/CommandCallback.h"

#import "im/actor/core/viewmodel/generics/StringValueModel.h"
#import "im/actor/core/viewmodel/generics/AvatarValueModel.h"
#import "im/actor/core/viewmodel/generics/BooleanValueModel.h"
#import "im/actor/core/viewmodel/generics/IntValueModel.h"
#import "im/actor/core/viewmodel/generics/UserPhoneValueModel.h"
#import "im/actor/core/viewmodel/generics/UserPresenceValueModel.h"
#import "im/actor/core/viewmodel/generics/ArrayListUserPhone.h"

#import "im/actor/core/i18n/I18NEngine.h"

#import "im/actor/core/network/RpcException.h"

#import "im/actor/core/api/ApiAuthSession.h"
#import "im/actor/core/api/ApiAuthHolder.h"

#import "im/actor/core/util/StringMatch.h"

// Core Runtime

#import "im/actor/runtime/Assets.h"
#import "im/actor/runtime/Crypto.h"
#import "im/actor/runtime/HTTP.h"
#import "im/actor/runtime/Log.h"
#import "im/actor/runtime/Network.h"
#import "im/actor/runtime/Runtime.h"
#import "im/actor/runtime/Storage.h"
#import "im/actor/runtime/StorageRuntime.h"
#import "im/actor/runtime/FileSystemRuntime.h"
#import "im/actor/runtime/HttpRuntime.h"
#import "im/actor/runtime/NetworkRuntime.h"
#import "im/actor/runtime/mtproto/ManagedConnection.h"
#import "im/actor/runtime/mtproto/ManagedNetworkProvider.h"
#import "im/actor/runtime/mtproto/AsyncConnection.h"
#import "im/actor/runtime/mtproto/AsyncConnectionFactory.h"
#import "im/actor/runtime/mtproto/AsyncConnectionInterface.h"
#import "im/actor/runtime/mtproto/ConnectionEndpoint.h"
#import "im/actor/runtime/cocoa/CocoaHttpProxyProvider.h"
#import "im/actor/runtime/cocoa/CocoaStorageProxyProvider.h"
#import "im/actor/runtime/cocoa/CocoaFileSystemProxyProvider.h"
#import "im/actor/runtime/cocoa/CocoaNetworkProxyProvider.h"

#import "im/actor/runtime/http/FileDownloadCallback.h"
#import "im/actor/runtime/http/FileUploadCallback.h"

#import "im/actor/runtime/files/FileReadCallback.h"
#import "im/actor/runtime/files/FileSystemReference.h"
#import "im/actor/runtime/files/InputFile.h"
#import "im/actor/runtime/files/OutputFile.h"

#import "im/actor/runtime/mvvm/AsyncVM.h"
#import "im/actor/runtime/mvvm/BaseValueModel.h"
#import "im/actor/runtime/mvvm/ModelChangedListener.h"
#import "im/actor/runtime/mvvm/MVVMCollection.h"
#import "im/actor/runtime/mvvm/ValueChangedListener.h"
#import "im/actor/runtime/mvvm/ValueDoubleChangedListener.h"
#import "im/actor/runtime/mvvm/ValueModel.h"
#import "im/actor/runtime/mvvm/ValueModelCreator.h"
#import "im/actor/runtime/mvvm/ValueTripleChangedListener.h"
#import "im/actor/runtime/mvvm/Value.h"

#import "im/actor/runtime/generic/mvvm/ListProcessor.h"
#import "im/actor/runtime/generic/mvvm/BindedDisplayList.h"
#import "im/actor/runtime/generic/mvvm/ChangeDescription.h"
#import "im/actor/runtime/generic/mvvm/DisplayList.h"
#import "im/actor/runtime/generic/mvvm/DisplayWindow.h"
#import "im/actor/runtime/generic/mvvm/AppleListUpdate.h"
#import "im/actor/runtime/generic/mvvm/AndroidListUpdate.h"
#import "im/actor/runtime/generic/mvvm/alg/Modification.h"
#import "im/actor/runtime/generic/mvvm/alg/Move.h"

#import "im/actor/runtime/storage/IndexStorage.h"
#import "im/actor/runtime/storage/KeyValueEngine.h"
#import "im/actor/runtime/storage/KeyValueItem.h"
#import "im/actor/runtime/storage/KeyValueRecord.h"
#import "im/actor/runtime/storage/KeyValueStorage.h"
#import "im/actor/runtime/storage/ListEngine.h"
#import "im/actor/runtime/storage/ListEngineDisplayExt.h"
#import "im/actor/runtime/storage/ListEngineDisplayListener.h"
#import "im/actor/runtime/storage/ListEngineDisplayLoadCallback.h"
#import "im/actor/runtime/storage/ListEngineItem.h"
#import "im/actor/runtime/storage/ListEngineRecord.h"
#import "im/actor/runtime/storage/ListStorage.h"
#import "im/actor/runtime/storage/ListStorageDisplayEx.h"
#import "im/actor/runtime/storage/PreferencesStorage.h"

#import "im/actor/runtime/markdown/MarkdownParser.h"
#import "im/actor/runtime/markdown/MDText.h"
#import "im/actor/runtime/markdown/MDRawText.h"
#import "im/actor/runtime/markdown/MDCode.h"
#import "im/actor/runtime/markdown/MDDocument.h"
#import "im/actor/runtime/markdown/MDSection.h"
#import "im/actor/runtime/markdown/MDSpan.h"
#import "im/actor/runtime/markdown/MDUrl.h"

#endif
