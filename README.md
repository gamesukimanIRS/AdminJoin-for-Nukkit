# AdminJoin for Nukkit
OPがログインした時、またログアウトした時のメッセージを設定することが可能になります。  
[Download](https://forum.mcbe.jp/resources/543/download)  

### 初期設定
| | メッセージ |
| :--: | :--- |
| ログイン時 | §aプレイヤー名(OP)§e が世界にやってきました |
| ログアウト時 | §aプレイヤー名(OP)§e が世界を去りました |

### コマンド
全てのコマンドはOP権限が必要です。  
{name}はプレイヤー名に置き換えられます。  
| コマンド | 説明 |
| :--: | :--- |
| `/setopjoin <String:メッセージ>` |	OPがログインした時のメッセージを設定します。 |
| `/setopquit <String:メッセージ>` |	OPがログアウトした時のメッセージを設定します。 |

### Config  
全ての項目はConfigでも設定可能です。  
```YAML
'#OPがログインした時のメッセージ。{name}はOPの名前に置き換えられます。': ''
opjoinmessage: §a{name}(OP)§e が世界にやってきました
'#OPがログアウトする時のメッセージ。{name}はOPの名前に置換。': ''
opquitmessage: §a{name}(OP)§e が世界を去りました
```

### 詳細
PlayerJoinEvent、PlayerQuitEventのsetJoin｜QuitMessageを使用しているため、同様のプラグインとはOPログイン時のみ競合する可能性があります。

### ライセンス
製作者偽りと二次配布、改造配布、プラグインの横流し、悪用を禁止します。  
自分用、又は友人用の改造のみ問題ありません。  
このプラグインで発生したすべての問題に対して製作者は責任は負いません。  
都合により、プラグインの破棄を要請する場合があります。その際は、要請に従いプラグインを破棄してください。   
