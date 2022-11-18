# Copyright (c) 2013-2016 The Bitcoin Core developers
# Distributed under the MIT software license, see the accompanying
# file COPYING or http://www.opensource.org/licenses/mit-license.php.

# Pattern rule to print variables, e.g. make print-top_srcdir
print-%: FORCE
	@echo '$*'='$($*)'

DIST_SUBDIRS = secp256k1

AM_LDFLAGS = $(LIBTOOL_LDFLAGS) $(HARDENED_LDFLAGS) $(GPROF_LDFLAGS) $(SANITIZER_LDFLAGS) $(LTO_LDFLAGS) $(CORE_LDFLAGS)
AM_CXXFLAGS = $(DEBUG_CXXFLAGS) $(HARDENED_CXXFLAGS) $(WARN_CXXFLAGS) $(NOWARN_CXXFLAGS) $(ERROR_CXXFLAGS) $(GPROF_CXXFLAGS) $(SANITIZER_CXXFLAGS) $(LTO_CXXFLAGS) $(CORE_CXXFLAGS)
AM_CPPFLAGS = $(DEBUG_CPPFLAGS) $(HARDENED_CPPFLAGS) $(CORE_CPPFLAGS)
AM_LIBTOOLFLAGS = --preserve-dup-deps
PTHREAD_FLAGS = $(PTHREAD_CFLAGS) $(PTHREAD_LIBS)
EXTRA_LIBRARIES =

lib_LTLIBRARIES =
noinst_LTLIBRARIES =

bin_PROGRAMS =
noinst_PROGRAMS =
TESTS =
BENCHMARKS =

FUJICOIN_INCLUDES=-I$(builddir) -I$(srcdir)/$(MINISKETCH_INCLUDE_DIR_INT) -I$(srcdir)/secp256k1/include -I$(srcdir)/$(UNIVALUE_INCLUDE_DIR_INT) $(LEVELDB_CPPFLAGS)

LIBFUJICOIN_NODE=libfujicoin_node.a
LIBFUJICOIN_COMMON=libfujicoin_common.a
LIBFUJICOIN_CONSENSUS=libfujicoin_consensus.a
LIBFUJICOIN_CLI=libfujicoin_cli.a
LIBFUJICOIN_UTIL=libfujicoin_util.a
LIBFUJICOIN_CRYPTO_BASE=crypto/libfujicoin_crypto_base.la
LIBFUJICOINQT=qt/libfujicoinqt.a
LIBSECP256K1=secp256k1/libsecp256k1.la

if ENABLE_ZMQ
LIBFUJICOIN_ZMQ=libfujicoin_zmq.a
endif
if BUILD_FUJICOIN_LIBS
LIBFUJICOINCONSENSUS=libfujicoinconsensus.la
endif
if BUILD_FUJICOIN_KERNEL_LIB
LIBFUJICOINKERNEL=libfujicoinkernel.la
endif
if ENABLE_WALLET
LIBFUJICOIN_WALLET=libfujicoin_wallet.a
LIBFUJICOIN_WALLET_TOOL=libfujicoin_wallet_tool.a
endif

LIBFUJICOIN_CRYPTO = $(LIBFUJICOIN_CRYPTO_BASE)
if ENABLE_SSE41
LIBFUJICOIN_CRYPTO_SSE41 = crypto/libfujicoin_crypto_sse41.la
LIBFUJICOIN_CRYPTO += $(LIBFUJICOIN_CRYPTO_SSE41)
endif
if ENABLE_AVX2
LIBFUJICOIN_CRYPTO_AVX2 = crypto/libfujicoin_crypto_avx2.la
LIBFUJICOIN_CRYPTO += $(LIBFUJICOIN_CRYPTO_AVX2)
endif
if ENABLE_X86_SHANI
LIBFUJICOIN_CRYPTO_X86_SHANI = crypto/libfujicoin_crypto_x86_shani.la
LIBFUJICOIN_CRYPTO += $(LIBFUJICOIN_CRYPTO_X86_SHANI)
endif
if ENABLE_ARM_SHANI
LIBFUJICOIN_CRYPTO_ARM_SHANI = crypto/libfujicoin_crypto_arm_shani.la
LIBFUJICOIN_CRYPTO += $(LIBFUJICOIN_CRYPTO_ARM_SHANI)
endif
noinst_LTLIBRARIES += $(LIBFUJICOIN_CRYPTO)

$(LIBSECP256K1): $(wildcard secp256k1/src/*.h) $(wildcard secp256k1/src/*.c) $(wildcard secp256k1/include/*)
	$(AM_V_at)$(MAKE) $(AM_MAKEFLAGS) -C $(@D) $(@F)

# Make is not made aware of per-object dependencies to avoid limiting building parallelization
# But to build the less dependent modules first, we manually select their order here:
EXTRA_LIBRARIES += \
  $(LIBFUJICOIN_UTIL) \
  $(LIBFUJICOIN_COMMON) \
  $(LIBFUJICOIN_CONSENSUS) \
  $(LIBFUJICOIN_NODE) \
  $(LIBFUJICOIN_CLI) \
  $(LIBFUJICOIN_IPC) \
  $(LIBFUJICOIN_WALLET) \
  $(LIBFUJICOIN_WALLET_TOOL) \
  $(LIBFUJICOIN_ZMQ)

if BUILD_FUJICOIND
  bin_PROGRAMS += fujicoind
endif

if BUILD_FUJICOIN_NODE
  bin_PROGRAMS += fujicoin-node
endif

if BUILD_FUJICOIN_CLI
  bin_PROGRAMS += fujicoin-cli
endif

if BUILD_FUJICOIN_TX
  bin_PROGRAMS += fujicoin-tx
endif

if ENABLE_WALLET
if BUILD_FUJICOIN_WALLET
  bin_PROGRAMS += fujicoin-wallet
endif
endif

if BUILD_FUJICOIN_UTIL
  bin_PROGRAMS += fujicoin-util
endif

if BUILD_FUJICOIN_CHAINSTATE
  bin_PROGRAMS += fujicoin-chainstate
endif

.PHONY: FORCE check-symbols check-security
# fujicoin core #
FUJICOIN_CORE_H = \
  addrdb.h \
  addrman.h \
  addrman_impl.h \
  attributes.h \
  banman.h \
  base58.h \
  bech32.h \
  blockencodings.h \
  blockfilter.h \
  chain.h \
  chainparams.h \
  chainparamsbase.h \
  chainparamsseeds.h \
  checkqueue.h \
  clientversion.h \
  coins.h \
  common/bloom.h \
  compat/assumptions.h \
  compat/byteswap.h \
  compat/compat.h \
  compat/cpuid.h \
  compat/endian.h \
  compressor.h \
  consensus/consensus.h \
  consensus/tx_check.h \
  consensus/tx_verify.h \
  core_io.h \
  core_memusage.h \
  cuckoocache.h \
  dbwrapper.h \
  deploymentinfo.h \
  deploymentstatus.h \
  external_signer.h \
  flatfile.h \
  fs.h \
  headerssync.h \
  httprpc.h \
  httpserver.h \
  i2p.h \
  index/base.h \
  index/blockfilterindex.h \
  index/coinstatsindex.h \
  index/disktxpos.h \
  index/txindex.h \
  indirectmap.h \
  init.h \
  init/common.h \
  interfaces/chain.h \
  interfaces/echo.h \
  interfaces/handler.h \
  interfaces/init.h \
  interfaces/ipc.h \
  interfaces/node.h \
  interfaces/wallet.h \
  kernel/chain.h \
  kernel/chainstatemanager_opts.h \
  kernel/checks.h \
  kernel/coinstats.h \
  kernel/context.h \
  kernel/mempool_limits.h \
  kernel/mempool_options.h \
  kernel/mempool_persist.h \
  kernel/validation_cache_sizes.h \
  key.h \
  key_io.h \
  logging.h \
  logging/timer.h \
  mapport.h \
  memusage.h \
  merkleblock.h \
  net.h \
  net_permissions.h \
  net_processing.h \
  net_types.h \
  netaddress.h \
  netbase.h \
  netgroup.h \
  netmessagemaker.h \
  node/blockstorage.h \
  node/caches.h \
  node/chainstate.h \
  node/coin.h \
  node/connection_types.h \
  node/context.h \
  node/eviction.h \
  node/interface_ui.h \
  node/mempool_args.h \
  node/mempool_persist_args.h \
  node/miner.h \
  node/minisketchwrapper.h \
  node/psbt.h \
  node/transaction.h \
  node/utxo_snapshot.h \
  node/validation_cache_args.h \
  noui.h \
  outputtype.h \
  policy/feerate.h \
  policy/fees.h \
  policy/fees_args.h \
  policy/packages.h \
  policy/policy.h \
  policy/rbf.h \
  policy/settings.h \
  pow.h \
  protocol.h \
  psbt.h \
  random.h \
  randomenv.h \
  rest.h \
  reverse_iterator.h \
  rpc/blockchain.h \
  rpc/client.h \
  rpc/mempool.h \
  rpc/mining.h \
  rpc/protocol.h \
  rpc/rawtransaction_util.h \
  rpc/register.h \
  rpc/request.h \
  rpc/server.h \
  rpc/server_util.h \
  rpc/util.h \
  scheduler.h \
  script/descriptor.h \
  script/keyorigin.h \
  script/miniscript.h \
  script/sigcache.h \
  script/sign.h \
  script/signingprovider.h \
  script/standard.h \
  shutdown.h \
  signet.h \
  streams.h \
  support/allocators/secure.h \
  support/allocators/zeroafterfree.h \
  support/cleanse.h \
  support/events.h \
  support/lockedpool.h \
  sync.h \
  threadinterrupt.h \
  threadsafety.h \
  timedata.h \
  torcontrol.h \
  txdb.h \
  txmempool.h \
  txorphanage.h \
  txrequest.h \
  undo.h \
  util/asmap.h \
  util/bip32.h \
  util/bitdeque.h \
  util/bytevectorhash.h \
  util/check.h \
  util/epochguard.h \
  util/error.h \
  util/fastrange.h \
  util/fees.h \
  util/getuniquepath.h \
  util/golombrice.h \
  util/hash_type.h \
  util/hasher.h \
  util/macros.h \
  util/message.h \
  util/moneystr.h \
  util/overflow.h \
  util/overloaded.h \
  util/rbf.h \
  util/readwritefile.h \
  util/result.h \
  util/serfloat.h \
  util/settings.h \
  util/sock.h \
  util/spanparsing.h \
  util/string.h \
  util/syscall_sandbox.h \
  util/syserror.h \
  util/system.h \
  util/thread.h \
  util/threadnames.h \
  util/time.h \
  util/tokenpipe.h \
  util/trace.h \
  util/translation.h \
  util/types.h \
  util/ui_change_type.h \
  util/url.h \
  util/vector.h \
  validation.h \
  validationinterface.h \
  versionbits.h \
  wallet/bdb.h \
  wallet/coincontrol.h \
  wallet/coinselection.h \
  wallet/context.h \
  wallet/crypter.h \
  wallet/db.h \
  wallet/dump.h \
  wallet/external_signer_scriptpubkeyman.h \
  wallet/feebumper.h \
  wallet/fees.h \
  wallet/ismine.h \
  wallet/load.h \
  wallet/receive.h \
  wallet/rpc/util.h \
  wallet/rpc/wallet.h \
  wallet/salvage.h \
  wallet/scriptpubkeyman.h \
  wallet/spend.h \
  wallet/sqlite.h \
  wallet/transaction.h \
  wallet/wallet.h \
  wallet/walletdb.h \
  wallet/wallettool.h \
  wallet/walletutil.h \
  walletinitinterface.h \
  warnings.h \
  zmq/zmqabstractnotifier.h \
  zmq/zmqnotificationinterface.h \
  zmq/zmqpublishnotifier.h \
  zmq/zmqrpc.h \
  zmq/zmqutil.h


obj/build.h: FORCE
	@$(MKDIR_P) $(builddir)/obj
	@$(top_srcdir)/share/genbuild.sh "$(abs_top_builddir)/src/obj/build.h" \
	  "$(abs_top_srcdir)"
libfujicoin_util_a-clientversion.$(OBJEXT): obj/build.h


# server: shared between fujicoind and fujicoin-qt
# Contains code accessing mempool and chain state that is meant to be separated
# from wallet and gui code (see node/README.md). Shared code should go in
# libfujicoin_common or libfujicoin_util libraries, instead.
libfujicoin_node_a_CPPFLAGS = $(AM_CPPFLAGS) $(FUJICOIN_INCLUDES) $(BOOST_CPPFLAGS) $(MINIUPNPC_CPPFLAGS) $(NATPMP_CPPFLAGS) $(EVENT_CFLAGS) $(EVENT_PTHREADS_CFLAGS)
libfujicoin_node_a_CXXFLAGS = $(AM_CXXFLAGS) $(PIE_FLAGS)
libfujicoin_node_a_SOURCES = \
  addrdb.cpp \
  addrman.cpp \
  banman.cpp \
  blockencodings.cpp \
  blockfilter.cpp \
  chain.cpp \
  consensus/tx_verify.cpp \
  dbwrapper.cpp \
  deploymentstatus.cpp \
  flatfile.cpp \
  headerssync.cpp \
  httprpc.cpp \
  httpserver.cpp \
  i2p.cpp \
  index/base.cpp \
  index/blockfilterindex.cpp \
  index/coinstatsindex.cpp \
  index/txindex.cpp \
  init.cpp \
  kernel/chain.cpp \
  kernel/checks.cpp \
  kernel/coinstats.cpp \
  kernel/context.cpp \
  kernel/mempool_persist.cpp \
  mapport.cpp \
  net.cpp \
  net_processing.cpp \
  netgroup.cpp \
  node/blockstorage.cpp \
  node/caches.cpp \
  node/chainstate.cpp \
  node/coin.cpp \
  node/connection_types.cpp \
  node/context.cpp \
  node/eviction.cpp \
  node/interface_ui.cpp \
  node/interfaces.cpp \
  node/mempool_args.cpp \
  node/mempool_persist_args.cpp \
  node/miner.cpp \
  node/minisketchwrapper.cpp \
  node/psbt.cpp \
  node/transaction.cpp \
  node/validation_cache_args.cpp \
  noui.cpp \
  policy/fees.cpp \
  policy/fees_args.cpp \
  policy/packages.cpp \
  policy/rbf.cpp \
  policy/settings.cpp \
  pow.cpp \
  rest.cpp \
  rpc/blockchain.cpp \
  rpc/fees.cpp \
  rpc/mempool.cpp \
  rpc/mining.cpp \
  rpc/net.cpp \
  rpc/node.cpp \
  rpc/output_script.cpp \
  rpc/rawtransaction.cpp \
  rpc/server.cpp \
  rpc/server_util.cpp \
  rpc/signmessage.cpp \
  rpc/txoutproof.cpp \
  script/sigcache.cpp \
  shutdown.cpp \
  signet.cpp \
  timedata.cpp \
  torcontrol.cpp \
  txdb.cpp \
  txmempool.cpp \
  txorphanage.cpp \
  txrequest.cpp \
  validation.cpp \
  validationinterface.cpp \
  versionbits.cpp \
  $(FUJICOIN_CORE_H)

if ENABLE_WALLET
libfujicoin_node_a_SOURCES += wallet/init.cpp
libfujicoin_node_a_CPPFLAGS += $(BDB_CPPFLAGS)
endif
if !ENABLE_WALLET
libfujicoin_node_a_SOURCES += dummywallet.cpp
endif

if ENABLE_ZMQ
libfujicoin_zmq_a_CPPFLAGS = $(AM_CPPFLAGS) $(FUJICOIN_INCLUDES) $(ZMQ_CFLAGS)
libfujicoin_zmq_a_CXXFLAGS = $(AM_CXXFLAGS) $(PIE_FLAGS)
libfujicoin_zmq_a_SOURCES = \
  zmq/zmqabstractnotifier.cpp \
  zmq/zmqnotificationinterface.cpp \
  zmq/zmqpublishnotifier.cpp \
  zmq/zmqrpc.cpp \
  zmq/zmqutil.cpp
endif


# wallet: shared between fujicoind and fujicoin-qt, but only linked
# when wallet enabled
libfujicoin_wallet_a_CPPFLAGS = $(AM_CPPFLAGS) $(FUJICOIN_INCLUDES) $(BOOST_CPPFLAGS) $(BDB_CPPFLAGS) $(SQLITE_CFLAGS)
libfujicoin_wallet_a_CXXFLAGS = $(AM_CXXFLAGS) $(PIE_FLAGS)
libfujicoin_wallet_a_SOURCES = \
  wallet/coincontrol.cpp \
  wallet/context.cpp \
  wallet/crypter.cpp \
  wallet/db.cpp \
  wallet/dump.cpp \
  wallet/external_signer_scriptpubkeyman.cpp \
  wallet/feebumper.cpp \
  wallet/fees.cpp \
  wallet/interfaces.cpp \
  wallet/load.cpp \
  wallet/receive.cpp \
  wallet/rpc/addresses.cpp \
  wallet/rpc/backup.cpp \
  wallet/rpc/coins.cpp \
  wallet/rpc/encrypt.cpp \
  wallet/rpc/spend.cpp \
  wallet/rpc/signmessage.cpp \
  wallet/rpc/transactions.cpp \
  wallet/rpc/util.cpp \
  wallet/rpc/wallet.cpp \
  wallet/scriptpubkeyman.cpp \
  wallet/spend.cpp \
  wallet/transaction.cpp \
  wallet/wallet.cpp \
  wallet/walletdb.cpp \
  wallet/walletutil.cpp \
  wallet/coinselection.cpp \
  $(FUJICOIN_CORE_H)

if USE_SQLITE
libfujicoin_wallet_a_SOURCES += wallet/sqlite.cpp
endif
if USE_BDB
libfujicoin_wallet_a_SOURCES += wallet/bdb.cpp wallet/salvage.cpp
endif

libfujicoin_wallet_tool_a_CPPFLAGS = $(AM_CPPFLAGS) $(FUJICOIN_INCLUDES) $(BOOST_CPPFLAGS)
libfujicoin_wallet_tool_a_CXXFLAGS = $(AM_CXXFLAGS) $(PIE_FLAGS)
libfujicoin_wallet_tool_a_SOURCES = \
  wallet/wallettool.cpp \
  $(FUJICOIN_CORE_H)

# crypto primitives library
crypto_libfujicoin_crypto_base_la_CPPFLAGS = $(AM_CPPFLAGS)

# Specify -static in both CXXFLAGS and LDFLAGS so libtool will only build a
# static version of this library. We don't need a dynamic version, and a dynamic
# version can't be used on windows anyway because the library doesn't currently
# export DLL symbols.
crypto_libfujicoin_crypto_base_la_CXXFLAGS = $(AM_CXXFLAGS) $(PIE_FLAGS) -static
crypto_libfujicoin_crypto_base_la_LDFLAGS = $(AM_LDFLAGS) -static

crypto_libfujicoin_crypto_base_la_SOURCES = \
  crypto/aes.cpp \
  crypto/aes.h \
  crypto/chacha_poly_aead.h \
  crypto/chacha_poly_aead.cpp \
  crypto/chacha20.h \
  crypto/chacha20.cpp \
  crypto/common.h \
  crypto/hkdf_sha256_32.cpp \
  crypto/hkdf_sha256_32.h \
  crypto/hmac_sha256.cpp \
  crypto/hmac_sha256.h \
  crypto/hmac_sha512.cpp \
  crypto/hmac_sha512.h \
  crypto/poly1305.h \
  crypto/poly1305.cpp \
  crypto/muhash.h \
  crypto/muhash.cpp \
  crypto/ripemd160.cpp \
  crypto/ripemd160.h \
  crypto/sha1.cpp \
  crypto/sha1.h \
  crypto/sha256.cpp \
  crypto/sha256.h \
  crypto/sha3.cpp \
  crypto/sha3.h \
  crypto/sha512.cpp \
  crypto/sha512.h \
  crypto/siphash.cpp \
  crypto/siphash.h

if USE_ASM
crypto_libfujicoin_crypto_base_la_SOURCES += crypto/sha256_sse4.cpp
endif

# See explanation for -static in crypto_libfujicoin_crypto_base_la's LDFLAGS and
# CXXFLAGS above
crypto_libfujicoin_crypto_sse41_la_LDFLAGS = $(AM_LDFLAGS) -static
crypto_libfujicoin_crypto_sse41_la_CXXFLAGS = $(AM_CXXFLAGS) $(PIE_FLAGS) -static
crypto_libfujicoin_crypto_sse41_la_CPPFLAGS = $(AM_CPPFLAGS)
crypto_libfujicoin_crypto_sse41_la_CXXFLAGS += $(SSE41_CXXFLAGS)
crypto_libfujicoin_crypto_sse41_la_CPPFLAGS += -DENABLE_SSE41
crypto_libfujicoin_crypto_sse41_la_SOURCES = crypto/sha256_sse41.cpp

# See explanation for -static in crypto_libfujicoin_crypto_base_la's LDFLAGS and
# CXXFLAGS above
crypto_libfujicoin_crypto_avx2_la_LDFLAGS = $(AM_LDFLAGS) -static
crypto_libfujicoin_crypto_avx2_la_CXXFLAGS = $(AM_CXXFLAGS) $(PIE_FLAGS) -static
crypto_libfujicoin_crypto_avx2_la_CPPFLAGS = $(AM_CPPFLAGS)
crypto_libfujicoin_crypto_avx2_la_CXXFLAGS += $(AVX2_CXXFLAGS)
crypto_libfujicoin_crypto_avx2_la_CPPFLAGS += -DENABLE_AVX2
crypto_libfujicoin_crypto_avx2_la_SOURCES = crypto/sha256_avx2.cpp

# See explanation for -static in crypto_libfujicoin_crypto_base_la's LDFLAGS and
# CXXFLAGS above
crypto_libfujicoin_crypto_x86_shani_la_LDFLAGS = $(AM_LDFLAGS) -static
crypto_libfujicoin_crypto_x86_shani_la_CXXFLAGS = $(AM_CXXFLAGS) $(PIE_FLAGS) -static
crypto_libfujicoin_crypto_x86_shani_la_CPPFLAGS = $(AM_CPPFLAGS)
crypto_libfujicoin_crypto_x86_shani_la_CXXFLAGS += $(X86_SHANI_CXXFLAGS)
crypto_libfujicoin_crypto_x86_shani_la_CPPFLAGS += -DENABLE_X86_SHANI
crypto_libfujicoin_crypto_x86_shani_la_SOURCES = crypto/sha256_x86_shani.cpp

# See explanation for -static in crypto_libfujicoin_crypto_base_la's LDFLAGS and
# CXXFLAGS above
crypto_libfujicoin_crypto_arm_shani_la_LDFLAGS = $(AM_LDFLAGS) -static
crypto_libfujicoin_crypto_arm_shani_la_CXXFLAGS = $(AM_CXXFLAGS) $(PIE_FLAGS) -static
crypto_libfujicoin_crypto_arm_shani_la_CPPFLAGS = $(AM_CPPFLAGS)
crypto_libfujicoin_crypto_arm_shani_la_CXXFLAGS += $(ARM_SHANI_CXXFLAGS)
crypto_libfujicoin_crypto_arm_shani_la_CPPFLAGS += -DENABLE_ARM_SHANI
crypto_libfujicoin_crypto_arm_shani_la_SOURCES = crypto/sha256_arm_shani.cpp

# consensus: shared between all executables that validate any consensus rules.
libfujicoin_consensus_a_CPPFLAGS = $(AM_CPPFLAGS) $(FUJICOIN_INCLUDES)
libfujicoin_consensus_a_CXXFLAGS = $(AM_CXXFLAGS) $(PIE_FLAGS)
libfujicoin_consensus_a_SOURCES = \
  arith_uint256.cpp \
  arith_uint256.h \
  consensus/amount.h \
  consensus/merkle.cpp \
  consensus/merkle.h \
  consensus/params.h \
  consensus/tx_check.cpp \
  consensus/validation.h \
  hash.cpp \
  hash.h \
  prevector.h \
  primitives/block.cpp \
  primitives/block.h \
  primitives/transaction.cpp \
  primitives/transaction.h \
  pubkey.cpp \
  pubkey.h \
  script/fujicoinconsensus.cpp \
  script/interpreter.cpp \
  script/interpreter.h \
  script/script.cpp \
  script/script.h \
  script/script_error.cpp \
  script/script_error.h \
  serialize.h \
  span.h \
  tinyformat.h \
  uint256.cpp \
  uint256.h \
  util/strencodings.cpp \
  util/strencodings.h \
  version.h

# common: shared between fujicoind, and fujicoin-qt and non-server tools
libfujicoin_common_a_CPPFLAGS = $(AM_CPPFLAGS) $(FUJICOIN_INCLUDES)
libfujicoin_common_a_CXXFLAGS = $(AM_CXXFLAGS) $(PIE_FLAGS)
libfujicoin_common_a_SOURCES = \
  base58.cpp \
  bech32.cpp \
  chainparams.cpp \
  coins.cpp \
  common/bloom.cpp \
  compressor.cpp \
  core_read.cpp \
  core_write.cpp \
  deploymentinfo.cpp \
  external_signer.cpp \
  init/common.cpp \
  key.cpp \
  key_io.cpp \
  merkleblock.cpp \
  net_types.cpp \
  netaddress.cpp \
  netbase.cpp \
  net_permissions.cpp \
  outputtype.cpp \
  policy/feerate.cpp \
  policy/policy.cpp \
  protocol.cpp \
  psbt.cpp \
  rpc/rawtransaction_util.cpp \
  rpc/external_signer.cpp \
  rpc/util.cpp \
  scheduler.cpp \
  script/descriptor.cpp \
  script/miniscript.cpp \
  script/sign.cpp \
  script/signingprovider.cpp \
  script/standard.cpp \
  warnings.cpp \
  $(FUJICOIN_CORE_H)

# util: shared between all executables.
libfujicoin_util_a_CPPFLAGS = $(AM_CPPFLAGS) $(FUJICOIN_INCLUDES) $(BOOST_CPPFLAGS)
libfujicoin_util_a_CXXFLAGS = $(AM_CXXFLAGS) $(PIE_FLAGS)
libfujicoin_util_a_SOURCES = \
  support/lockedpool.cpp \
  chainparamsbase.cpp \
  clientversion.cpp \
  fs.cpp \
  interfaces/echo.cpp \
  interfaces/handler.cpp \
  interfaces/init.cpp \
  logging.cpp \
  random.cpp \
  randomenv.cpp \
  rpc/request.cpp \
  support/cleanse.cpp \
  sync.cpp \
  threadinterrupt.cpp \
  util/asmap.cpp \
  util/bip32.cpp \
  util/bytevectorhash.cpp \
  util/check.cpp \
  util/error.cpp \
  util/fees.cpp \
  util/getuniquepath.cpp \
  util/hasher.cpp \
  util/sock.cpp \
  util/syserror.cpp \
  util/system.cpp \
  util/message.cpp \
  util/moneystr.cpp \
  util/rbf.cpp \
  util/readwritefile.cpp \
  util/settings.cpp \
  util/thread.cpp \
  util/threadnames.cpp \
  util/serfloat.cpp \
  util/spanparsing.cpp \
  util/strencodings.cpp \
  util/string.cpp \
  util/syscall_sandbox.cpp \
  util/time.cpp \
  util/tokenpipe.cpp \
  $(FUJICOIN_CORE_H)

if USE_LIBEVENT
libfujicoin_util_a_SOURCES += util/url.cpp
endif

# cli: shared between fujicoin-cli and fujicoin-qt
libfujicoin_cli_a_CPPFLAGS = $(AM_CPPFLAGS) $(FUJICOIN_INCLUDES)
libfujicoin_cli_a_CXXFLAGS = $(AM_CXXFLAGS) $(PIE_FLAGS)
libfujicoin_cli_a_SOURCES = \
  compat/stdin.h \
  compat/stdin.cpp \
  rpc/client.cpp \
  $(FUJICOIN_CORE_H)

nodist_libfujicoin_util_a_SOURCES = $(srcdir)/obj/build.h
#

# fujicoind & fujicoin-node binaries #
fujicoin_daemon_sources = fujicoind.cpp
fujicoin_bin_cppflags = $(AM_CPPFLAGS) $(FUJICOIN_INCLUDES)
fujicoin_bin_cxxflags = $(AM_CXXFLAGS) $(PIE_FLAGS)
fujicoin_bin_ldflags = $(RELDFLAGS) $(AM_LDFLAGS) $(LIBTOOL_APP_LDFLAGS) $(PTHREAD_FLAGS)

if TARGET_WINDOWS
fujicoin_daemon_sources += fujicoind-res.rc
endif

fujicoin_bin_ldadd = \
  $(LIBFUJICOIN_WALLET) \
  $(LIBFUJICOIN_COMMON) \
  $(LIBFUJICOIN_UTIL) \
  $(LIBUNIVALUE) \
  $(LIBFUJICOIN_ZMQ) \
  $(LIBFUJICOIN_CONSENSUS) \
  $(LIBFUJICOIN_CRYPTO) \
  $(LIBLEVELDB) \
  $(LIBMEMENV) \
  $(LIBSECP256K1)

fujicoin_bin_ldadd += $(BDB_LIBS) $(MINIUPNPC_LIBS) $(NATPMP_LIBS) $(EVENT_PTHREADS_LIBS) $(EVENT_LIBS) $(ZMQ_LIBS) $(SQLITE_LIBS)

fujicoind_SOURCES = $(fujicoin_daemon_sources) init/fujicoind.cpp
fujicoind_CPPFLAGS = $(fujicoin_bin_cppflags)
fujicoind_CXXFLAGS = $(fujicoin_bin_cxxflags)
fujicoind_LDFLAGS = $(fujicoin_bin_ldflags)
fujicoind_LDADD = $(LIBFUJICOIN_NODE) $(fujicoin_bin_ldadd)

fujicoin_node_SOURCES = $(fujicoin_daemon_sources) init/fujicoin-node.cpp
fujicoin_node_CPPFLAGS = $(fujicoin_bin_cppflags)
fujicoin_node_CXXFLAGS = $(fujicoin_bin_cxxflags)
fujicoin_node_LDFLAGS = $(fujicoin_bin_ldflags)
fujicoin_node_LDADD = $(LIBFUJICOIN_NODE) $(fujicoin_bin_ldadd) $(LIBFUJICOIN_IPC) $(LIBMULTIPROCESS_LIBS)

# fujicoin-cli binary #
fujicoin_cli_SOURCES = fujicoin-cli.cpp
fujicoin_cli_CPPFLAGS = $(AM_CPPFLAGS) $(FUJICOIN_INCLUDES) $(EVENT_CFLAGS)
fujicoin_cli_CXXFLAGS = $(AM_CXXFLAGS) $(PIE_FLAGS)
fujicoin_cli_LDFLAGS = $(RELDFLAGS) $(AM_LDFLAGS) $(LIBTOOL_APP_LDFLAGS) $(PTHREAD_FLAGS)

if TARGET_WINDOWS
fujicoin_cli_SOURCES += fujicoin-cli-res.rc
endif

fujicoin_cli_LDADD = \
  $(LIBFUJICOIN_CLI) \
  $(LIBUNIVALUE) \
  $(LIBFUJICOIN_UTIL) \
  $(LIBFUJICOIN_CRYPTO)

fujicoin_cli_LDADD += $(EVENT_LIBS)
#

# fujicoin-tx binary #
fujicoin_tx_SOURCES = fujicoin-tx.cpp
fujicoin_tx_CPPFLAGS = $(AM_CPPFLAGS) $(FUJICOIN_INCLUDES)
fujicoin_tx_CXXFLAGS = $(AM_CXXFLAGS) $(PIE_FLAGS)
fujicoin_tx_LDFLAGS = $(RELDFLAGS) $(AM_LDFLAGS) $(LIBTOOL_APP_LDFLAGS) $(PTHREAD_FLAGS)

if TARGET_WINDOWS
fujicoin_tx_SOURCES += fujicoin-tx-res.rc
endif

fujicoin_tx_LDADD = \
  $(LIBUNIVALUE) \
  $(LIBFUJICOIN_COMMON) \
  $(LIBFUJICOIN_UTIL) \
  $(LIBFUJICOIN_CONSENSUS) \
  $(LIBFUJICOIN_CRYPTO) \
  $(LIBSECP256K1)
#

# fujicoin-wallet binary #
fujicoin_wallet_SOURCES = fujicoin-wallet.cpp
fujicoin_wallet_SOURCES += init/fujicoin-wallet.cpp
fujicoin_wallet_CPPFLAGS = $(fujicoin_bin_cppflags)
fujicoin_wallet_CXXFLAGS = $(fujicoin_bin_cxxflags)
fujicoin_wallet_LDFLAGS = $(fujicoin_bin_ldflags)
fujicoin_wallet_LDADD = \
  $(LIBFUJICOIN_WALLET_TOOL) \
  $(LIBFUJICOIN_WALLET) \
  $(LIBFUJICOIN_COMMON) \
  $(LIBFUJICOIN_UTIL) \
  $(LIBUNIVALUE) \
  $(LIBFUJICOIN_CONSENSUS) \
  $(LIBFUJICOIN_CRYPTO) \
  $(LIBSECP256K1) \
  $(BDB_LIBS) \
  $(SQLITE_LIBS)

if TARGET_WINDOWS
fujicoin_wallet_SOURCES += fujicoin-wallet-res.rc
endif
#

# fujicoin-util binary #
fujicoin_util_SOURCES = fujicoin-util.cpp
fujicoin_util_CPPFLAGS = $(AM_CPPFLAGS) $(FUJICOIN_INCLUDES)
fujicoin_util_CXXFLAGS = $(AM_CXXFLAGS) $(PIE_FLAGS)
fujicoin_util_LDFLAGS = $(RELDFLAGS) $(AM_LDFLAGS) $(LIBTOOL_APP_LDFLAGS) $(PTHREAD_FLAGS)

if TARGET_WINDOWS
fujicoin_util_SOURCES += fujicoin-util-res.rc
endif

fujicoin_util_LDADD = \
  $(LIBFUJICOIN_COMMON) \
  $(LIBFUJICOIN_UTIL) \
  $(LIBUNIVALUE) \
  $(LIBFUJICOIN_CONSENSUS) \
  $(LIBFUJICOIN_CRYPTO) \
  $(LIBSECP256K1)
#

# fujicoin-chainstate binary #
fujicoin_chainstate_SOURCES = fujicoin-chainstate.cpp
fujicoin_chainstate_CPPFLAGS = $(AM_CPPFLAGS) $(FUJICOIN_INCLUDES) $(BOOST_CPPFLAGS)
fujicoin_chainstate_CXXFLAGS = $(AM_CXXFLAGS) $(PIE_FLAGS)

# $(LIBTOOL_APP_LDFLAGS) deliberately omitted here so that we can test linking
# fujicoin-chainstate against libfujicoinkernel as a shared or static library by
# setting --{en,dis}able-shared.
fujicoin_chainstate_LDFLAGS = $(RELDFLAGS) $(AM_LDFLAGS) $(PTHREAD_FLAGS)
fujicoin_chainstate_LDADD = $(LIBFUJICOINKERNEL)
#

# fujicoinkernel library #
if BUILD_FUJICOIN_KERNEL_LIB
lib_LTLIBRARIES += $(LIBFUJICOINKERNEL)

libfujicoinkernel_la_LDFLAGS = $(AM_LDFLAGS) -no-undefined $(RELDFLAGS) $(PTHREAD_FLAGS)
libfujicoinkernel_la_LIBADD = $(LIBFUJICOIN_CRYPTO) $(LIBUNIVALUE) $(LIBLEVELDB) $(LIBMEMENV) $(LIBSECP256K1)
libfujicoinkernel_la_CPPFLAGS = $(AM_CPPFLAGS) -I$(builddir)/obj -I$(srcdir)/secp256k1/include -DBUILD_FUJICOIN_INTERNAL $(BOOST_CPPFLAGS) $(LEVELDB_CPPFLAGS) -I$(srcdir)/$(UNIVALUE_INCLUDE_DIR_INT)

# libfujicoinkernel requires default symbol visibility, explicitly specify that
# here so that things still work even when user configures with
#   --enable-reduce-exports
#
# Note this is a quick hack that will be removed as we incrementally define what
# to export from the library.
libfujicoinkernel_la_CXXFLAGS = $(AM_CXXFLAGS) $(PIE_FLAGS) -fvisibility=default

# TODO: For now, Specify -static in both CXXFLAGS and LDFLAGS when building for
#       windows targets so libtool will only build a static version of this
#       library. There are unresolved problems when building dll's for mingw-w64
#       and attempting to statically embed libstdc++, libpthread, etc.
if TARGET_WINDOWS
libfujicoinkernel_la_LDFLAGS += -static
libfujicoinkernel_la_CXXFLAGS += -static
endif

# TODO: libfujicoinkernel is a work in progress consensus engine library, as more
#       and more modules are decoupled from the consensus engine, this list will
#       shrink to only those which are absolutely necessary.
libfujicoinkernel_la_SOURCES = \
  kernel/fujicoinkernel.cpp \
  arith_uint256.cpp \
  chain.cpp \
  chainparamsbase.cpp \
  chainparams.cpp \
  clientversion.cpp \
  coins.cpp \
  compressor.cpp \
  consensus/merkle.cpp \
  consensus/tx_check.cpp \
  consensus/tx_verify.cpp \
  core_read.cpp \
  dbwrapper.cpp \
  deploymentinfo.cpp \
  deploymentstatus.cpp \
  flatfile.cpp \
  fs.cpp \
  hash.cpp \
  kernel/chain.cpp \
  kernel/checks.cpp \
  kernel/coinstats.cpp \
  kernel/context.cpp \
  kernel/mempool_persist.cpp \
  key.cpp \
  logging.cpp \
  node/blockstorage.cpp \
  node/chainstate.cpp \
  node/interface_ui.cpp \
  policy/feerate.cpp \
  policy/fees.cpp \
  policy/packages.cpp \
  policy/policy.cpp \
  policy/rbf.cpp \
  policy/settings.cpp \
  pow.cpp \
  primitives/block.cpp \
  primitives/transaction.cpp \
  pubkey.cpp \
  random.cpp \
  randomenv.cpp \
  scheduler.cpp \
  script/interpreter.cpp \
  script/script.cpp \
  script/script_error.cpp \
  script/sigcache.cpp \
  script/standard.cpp \
  shutdown.cpp \
  signet.cpp \
  support/cleanse.cpp \
  support/lockedpool.cpp \
  sync.cpp \
  threadinterrupt.cpp \
  txdb.cpp \
  txmempool.cpp \
  uint256.cpp \
  util/bytevectorhash.cpp \
  util/check.cpp \
  util/getuniquepath.cpp \
  util/hasher.cpp \
  util/moneystr.cpp \
  util/rbf.cpp \
  util/serfloat.cpp \
  util/settings.cpp \
  util/strencodings.cpp \
  util/string.cpp \
  util/syscall_sandbox.cpp \
  util/syserror.cpp \
  util/system.cpp \
  util/thread.cpp \
  util/threadnames.cpp \
  util/time.cpp \
  util/tokenpipe.cpp \
  validation.cpp \
  validationinterface.cpp \
  versionbits.cpp \
  warnings.cpp

# Required for obj/build.h to be generated first.
# More details: https://www.gnu.org/software/automake/manual/html_node/Built-Sources-Example.html
libfujicoinkernel_la-clientversion.l$(OBJEXT): obj/build.h
endif # BUILD_FUJICOIN_KERNEL_LIB
#

# fujicoinconsensus library #
if BUILD_FUJICOIN_LIBS
lib_LTLIBRARIES += $(LIBFUJICOINCONSENSUS)

include_HEADERS = script/fujicoinconsensus.h
libfujicoinconsensus_la_SOURCES = support/cleanse.cpp $(crypto_libfujicoin_crypto_base_la_SOURCES) $(libfujicoin_consensus_a_SOURCES)

libfujicoinconsensus_la_LDFLAGS = $(AM_LDFLAGS) -no-undefined $(RELDFLAGS)
libfujicoinconsensus_la_LIBADD = $(LIBSECP256K1)
libfujicoinconsensus_la_CPPFLAGS = $(AM_CPPFLAGS) -I$(builddir)/obj -I$(srcdir)/secp256k1/include -DBUILD_FUJICOIN_INTERNAL
libfujicoinconsensus_la_CXXFLAGS = $(AM_CXXFLAGS) $(PIE_FLAGS)

endif
#

CTAES_DIST =  crypto/ctaes/bench.c
CTAES_DIST += crypto/ctaes/ctaes.c
CTAES_DIST += crypto/ctaes/ctaes.h
CTAES_DIST += crypto/ctaes/README.md
CTAES_DIST += crypto/ctaes/test.c

CLEANFILES = $(EXTRA_LIBRARIES)

CLEANFILES += *.gcda *.gcno
CLEANFILES += compat/*.gcda compat/*.gcno
CLEANFILES += consensus/*.gcda consensus/*.gcno
CLEANFILES += crc32c/src/*.gcda crc32c/src/*.gcno
CLEANFILES += crypto/*.gcda crypto/*.gcno
CLEANFILES += index/*.gcda index/*.gcno
CLEANFILES += interfaces/*.gcda interfaces/*.gcno
CLEANFILES += node/*.gcda node/*.gcno
CLEANFILES += policy/*.gcda policy/*.gcno
CLEANFILES += primitives/*.gcda primitives/*.gcno
CLEANFILES += rpc/*.gcda rpc/*.gcno
CLEANFILES += script/*.gcda script/*.gcno
CLEANFILES += support/*.gcda support/*.gcno
CLEANFILES += univalue/*.gcda univalue/*.gcno
CLEANFILES += util/*.gcda util/*.gcno
CLEANFILES += wallet/*.gcda wallet/*.gcno
CLEANFILES += wallet/test/*.gcda wallet/test/*.gcno
CLEANFILES += zmq/*.gcda zmq/*.gcno
CLEANFILES += obj/build.h

EXTRA_DIST = $(CTAES_DIST)


config/fujicoin-config.h: config/stamp-h1
	@$(MAKE) -C $(top_builddir) $(subdir)/$(@)
config/stamp-h1: $(top_srcdir)/$(subdir)/config/fujicoin-config.h.in $(top_builddir)/config.status
	$(AM_V_at)$(MAKE) -C $(top_builddir) $(subdir)/$(@)
$(top_srcdir)/$(subdir)/config/fujicoin-config.h.in:  $(am__configure_deps)
	$(AM_V_at)$(MAKE) -C $(top_srcdir) $(subdir)/config/fujicoin-config.h.in

clean-local:
	-$(MAKE) -C secp256k1 clean
	-rm -f leveldb/*/*.gcda leveldb/*/*.gcno leveldb/helpers/memenv/*.gcda leveldb/helpers/memenv/*.gcno
	-rm -f config.h
	-rm -rf test/__pycache__

.rc.o:
	@test -f $(WINDRES)
	## FIXME: How to get the appropriate modulename_CPPFLAGS in here?
	$(AM_V_GEN) $(WINDRES) $(DEFS) $(DEFAULT_INCLUDES) $(INCLUDES) $(CPPFLAGS) -DWINDRES_PREPROC -i $< -o $@

check-symbols: $(bin_PROGRAMS)
	@echo "Running symbol and dynamic library checks..."
	$(AM_V_at) $(PYTHON) $(top_srcdir)/contrib/devtools/symbol-check.py $(bin_PROGRAMS)

check-security: $(bin_PROGRAMS)
if HARDEN
	@echo "Checking binary security..."
	$(AM_V_at) $(PYTHON) $(top_srcdir)/contrib/devtools/security-check.py $(bin_PROGRAMS)
endif

libfujicoin_ipc_mpgen_input = \
  ipc/capnp/echo.capnp \
  ipc/capnp/init.capnp
EXTRA_DIST += $(libfujicoin_ipc_mpgen_input)
%.capnp:

# Explicitly list dependencies on generated headers as described in
# https://www.gnu.org/software/automake/manual/html_node/Built-Sources-Example.html#Recording-Dependencies-manually
ipc/capnp/libfujicoin_ipc_a-protocol.$(OBJEXT): $(libfujicoin_ipc_mpgen_input:=.h)

if BUILD_MULTIPROCESS
LIBFUJICOIN_IPC=libfujicoin_ipc.a
libfujicoin_ipc_a_SOURCES = \
  ipc/capnp/context.h \
  ipc/capnp/init-types.h \
  ipc/capnp/protocol.cpp \
  ipc/capnp/protocol.h \
  ipc/context.h \
  ipc/exception.h \
  ipc/interfaces.cpp \
  ipc/process.cpp \
  ipc/process.h \
  ipc/protocol.h
libfujicoin_ipc_a_CPPFLAGS = $(AM_CPPFLAGS) $(FUJICOIN_INCLUDES)
libfujicoin_ipc_a_CXXFLAGS = $(AM_CXXFLAGS) $(PIE_FLAGS) $(LIBMULTIPROCESS_CFLAGS)

include $(MPGEN_PREFIX)/include/mpgen.mk
libfujicoin_ipc_mpgen_output = \
  $(libfujicoin_ipc_mpgen_input:=.c++) \
  $(libfujicoin_ipc_mpgen_input:=.h) \
  $(libfujicoin_ipc_mpgen_input:=.proxy-client.c++) \
  $(libfujicoin_ipc_mpgen_input:=.proxy-server.c++) \
  $(libfujicoin_ipc_mpgen_input:=.proxy-types.c++) \
  $(libfujicoin_ipc_mpgen_input:=.proxy-types.h) \
  $(libfujicoin_ipc_mpgen_input:=.proxy.h)
nodist_libfujicoin_ipc_a_SOURCES = $(libfujicoin_ipc_mpgen_output)
CLEANFILES += $(libfujicoin_ipc_mpgen_output)
endif

%.raw.h: %.raw
	@$(MKDIR_P) $(@D)
	@{ \
	 echo "static unsigned const char $(*F)_raw[] = {" && \
	 $(HEXDUMP) -v -e '8/1 "0x%02x, "' -e '"\n"' $< | $(SED) -e 's/0x  ,//g' && \
	 echo "};"; \
	} > "$@.new" && mv -f "$@.new" "$@"
	@echo "Generated $@"

include Makefile.minisketch.include

include Makefile.crc32c.include
include Makefile.leveldb.include

include Makefile.test_util.include
include Makefile.test_fuzz.include

include Makefile.test.include

if ENABLE_BENCH
include Makefile.bench.include
endif

if ENABLE_QT
include Makefile.qt.include
endif

if ENABLE_QT_TESTS
include Makefile.qttest.include
endif

include Makefile.univalue.include
